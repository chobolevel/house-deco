package kr.co.deco.house.controller;

import kr.co.deco.house.entity.member.Member;
import kr.co.deco.house.exception.ApiException;
import kr.co.deco.house.properties.SocialAppProperties;
import kr.co.deco.house.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    private final SocialAppProperties appProperties;

    @GetMapping("token")
    public String token(@RequestParam("code") String code, RedirectAttributes rttr) throws ApiException {
        RestTemplate restTemplate = new RestTemplate();

        // code를 통해 access_token 요청
        // header
        HttpHeaders firstHeaders = new HttpHeaders();
        firstHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // params
        MultiValueMap<String, String> firstParams = new LinkedMultiValueMap<>();
        firstParams.set("grant_type", "authorization_code");
        firstParams.set("client_id", appProperties.getKakao().getClientId());
        firstParams.set("redirect_uri", "http://localhost:8080/auth/token");
        firstParams.set("code", code);

        HttpEntity<MultiValueMap<String, String>> firstRequest = new HttpEntity<>(firstParams, firstHeaders);

        ResponseEntity<Map> firstRes = restTemplate.postForEntity("https://kauth.kakao.com/oauth/token", firstRequest, Map.class);

        // access_token을 통해 email 조회
        HttpHeaders secondHeaders = new HttpHeaders();
        secondHeaders.setBearerAuth((String) firstRes.getBody().get("access_token"));

        MultiValueMap<String, String> secondParams = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> secondRequest = new HttpEntity<>(secondParams, secondHeaders);

        ResponseEntity<Map> secondRes = restTemplate.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.GET, secondRequest, Map.class);

        Map kakaoAccount = (Map) secondRes.getBody().get("kakao_account");
        String email = (String) kakaoAccount.get("email");

        Member findMember = memberService.findOne(Member.builder().email(email).build());

        if(findMember != null) {
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(new UsernamePasswordAuthenticationToken(
                    findMember,
                    null,
                    findMember.getAuthorities()
            ));
            SecurityContextHolder.setContext(context);
            return "redirect:/";
        }
        rttr.addAttribute("error", true);
        rttr.addAttribute("msg", "가입되지 않은 회원입니다. 관리자에게 문의해 주세요.");
        return "redirect:/signin";
    }

    @GetMapping("naver")
    public String naverLogin(@RequestParam("code") String code, RedirectAttributes rttr) throws ApiException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders firstHeaders = new HttpHeaders();
        firstHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> firstBody = new LinkedMultiValueMap<>();
        firstBody.set("grant_type", "authorization_code");
        firstBody.set("client_id", appProperties.getNaver().getClientId());
        firstBody.set("client_secret", appProperties.getNaver().getClientSecret());
        firstBody.set("code", code);

        HttpEntity<MultiValueMap<String, String>> firstReq = new HttpEntity<>(firstBody, firstHeaders);

        ResponseEntity<Map> firstRes = restTemplate.postForEntity("https://nid.naver.com/oauth2.0/token", firstReq, Map.class);
        String accessToken = String.valueOf(firstRes.getBody().get("access_token"));

        HttpHeaders secondHeaders = new HttpHeaders();
        secondHeaders.setBearerAuth(accessToken);

        MultiValueMap<String, String> secondParams = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> secondReq = new HttpEntity<>(secondParams, secondHeaders);

        ResponseEntity<Map> secondRes = restTemplate.exchange("https://openapi.naver.com/v1/nid/me", HttpMethod.GET, secondReq, Map.class);

        Map response = (Map) secondRes.getBody().get("response");
        String email =  String.valueOf(response.get("email"));

        Member findMember = memberService.findOne(Member.builder().email(email).build());

        if(findMember != null) {
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(new UsernamePasswordAuthenticationToken(
                    findMember,
                    null,
                    findMember.getAuthorities()
            ));
            SecurityContextHolder.setContext(context);
            return "redirect:/";
        }
        rttr.addAttribute("error", true);
        rttr.addAttribute("msg", "가입되지 않은 회원입니다. 관리자에게 문의해 주세요.");
        return "redirect:/signin";
    }

}
