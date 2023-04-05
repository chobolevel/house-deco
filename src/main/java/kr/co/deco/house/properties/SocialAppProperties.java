package kr.co.deco.house.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConfigurationProperties("social.app")
public class SocialAppProperties {

    private Kakao kakao;
    private Naver naver;
    private Microsoft microsoft;

    @ConstructorBinding
    public SocialAppProperties(Kakao kakao, Naver naver, Microsoft microsoft) {
        this.kakao = kakao;
        this.naver = naver;
        this.microsoft = microsoft;
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

    @Getter
    public static class Microsoft {
        private String clientId;
        private String clientSecret;
        private String redirectUri;

        @ConstructorBinding
        public Microsoft(String clientId, String clientSecret, String redirectUri) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
            this.redirectUri = redirectUri;
        }
    }

}
