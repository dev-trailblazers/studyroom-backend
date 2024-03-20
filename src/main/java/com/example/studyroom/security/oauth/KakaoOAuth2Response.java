package com.example.studyroom.security.oauth;

import java.time.LocalDate;
import java.util.Map;

public record KakaoOAuth2Response(
        Long id,
        KakaoAccount kakaoAccount
) {
    public record KakaoAccount(
            String name,
            LocalDate birth
    ) {


//        public static KakaoAccount from(Map<String, Object> attributes) {
//            return new KakaoAccount(
//                    String.valueOf(attributes.get("name")),
//                    LocalDate.of(
//                            Integer.parseInt(String.valueOf(attributes.get("birthyear"))),
//                            Integer.parseInt(String.valueOf(attributes.get("birthday")).substring(0, 2)),
//                            Integer.parseInt(String.valueOf(attributes.get("birthday")).substring(2))
//                    )
//            );
//        }

        //todo: 카카오 사업자 등록 전까지만 사용
        public static KakaoAccount from(Map<String, Object> attributes) {
            return new KakaoAccount("홍길동", LocalDate.of(1999,11,23));
        }
    }

    public static KakaoOAuth2Response from(Map<String, Object> attributes) {
        return new KakaoOAuth2Response(
                Long.valueOf(String.valueOf(attributes.get("id"))),
                KakaoAccount.from((Map<String, Object>) attributes.get("kakao_account"))
        );
    }

}
