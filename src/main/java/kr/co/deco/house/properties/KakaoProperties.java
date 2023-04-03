package kr.co.deco.house.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties("kakao.app")
public class KakaoProperties {

    private String clientId;

}
