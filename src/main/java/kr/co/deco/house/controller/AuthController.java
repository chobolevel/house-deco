package kr.co.deco.house.controller;

import kr.co.deco.house.entity.member.Member;
import kr.co.deco.house.enums.common.ApiExceptionType;
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
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("kakao")
    public String token(@RequestParam("code") String code, RedirectAttributes rttr) throws ApiException {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.set("grant_type", "authorization_code");
        body.set("client_id", appProperties.getKakao().getClientId());
        body.set("redirect_uri", "http://localhost:8080/auth/kakao");
        body.set("code", code);

        String accessToken = this.getAccessToken("https://kauth.kakao.com/oauth/token", body);

        String email = this.getEmail("https://kapi.kakao.com/v2/user/me", accessToken, "kakao_account");

        return this.redirecting(email, rttr);
    }

    @GetMapping("naver")
    public String naverLogin(@RequestParam("code") String code, RedirectAttributes rttr) throws ApiException {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.set("grant_type", "authorization_code");
        body.set("client_id", appProperties.getNaver().getClientId());
        body.set("client_secret", appProperties.getNaver().getClientSecret());
        body.set("code", code);

        String accessToken = this.getAccessToken("https://nid.naver.com/oauth2.0/token", body);

        String email =  this.getEmail("https://openapi.naver.com/v1/nid/me", accessToken, "response");

        return this.redirecting(email, rttr);
    }

    @GetMapping("ms")
    public String msLogin(@RequestParam("code") String code, RedirectAttributes rttr) throws ApiException {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.set("client_id", appProperties.getMicrosoft().getClientId());
        body.set("client_secret", appProperties.getMicrosoft().getClientSecret());
        body.set("scope", "user.read profile openid offline_access");
        body.set("code", code);
        body.set("redirect_uri", "http://localhost:8080/auth/ms");
        body.set("grant_type", "authorization_code");

        String accessToken = this.getAccessToken("https://login.microsoftonline.com/common/oauth2/v2.0/token", body);

        String email = this.getEmail("https://graph.microsoft.com/v1.0/me", accessToken, "");

        return this.redirecting(email, rttr);
    }

    private String getAccessToken(String url, MultiValueMap<String, String> body) throws ApiException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(body, headers);

        ResponseEntity<Map> res = restTemplate.postForEntity(url, req, Map.class);

        if(res.getBody() == null) {
            throw new ApiException(ApiExceptionType.CANNOT_FETCH_ACCESS_TOKEN, "API");
        }

        return String.valueOf(res.getBody().get("access_token"));
    }

    private String getEmail(String url, String accessToken, String accessVar) throws ApiException {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        ResponseEntity<Map> res = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(headers), Map.class);

        if(res.getBody() == null) {
            throw new ApiException(ApiExceptionType.CANNOT_FETCH_EMAIL, "API");
        }

        if(accessVar == null || accessVar.trim().equals("")) {
            return String.valueOf(res.getBody().get("mail"));
        }

        Map data = (Map) res.getBody().get(accessVar);
        return String.valueOf(data.get("email"));
    }

    private void makeSecurityContext(Member member) {
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(
                member,
                null,
                member.getAuthorities()
        ));
        SecurityContextHolder.setContext(context);
    }

    private String redirecting(String email, RedirectAttributes rttr) throws ApiException {
        Member findMember = memberService.findOne(Member.builder().email(email).build());

        if(findMember != null) {
            this.makeSecurityContext(findMember);
            return "redirect:/";
        }
        rttr.addAttribute("error", true);
        rttr.addAttribute("errorMsg", "가입되지 않은 회원입니다. 관리자에게 문의해 주세요.");
        return "redirect:/signin";
    }

}
