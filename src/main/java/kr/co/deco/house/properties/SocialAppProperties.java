package kr.co.deco.house.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConfigurationProperties("social.app")
public class SocialAppProperties {

    private Kakao kakao;
    private Naver naver;

    @ConstructorBinding
    public SocialAppProperties(Kakao kakao, Naver naver) {
        this.kakao = kakao;
        this.naver = naver;
    }

    @Getter
    public static class Kakao {
        private String clientId;
        private String redirectUri;

        @ConstructorBinding
        public Kakao(String clientId, String redirectUri) {
            this.clientId = clientId;
            this.redirectUri = redirectUri;
        }
    }

    @Getter
    public static class Naver {
        private String clientId;
        private String clientSecret;
        private String redirectUri;

        @ConstructorBinding

        public Naver(String clientId, String clientSecret, String redirectUri) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
            this.redirectUri = redirectUri;
        }
    }

}
