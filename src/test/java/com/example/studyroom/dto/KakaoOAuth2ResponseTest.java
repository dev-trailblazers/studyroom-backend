package com.example.studyroom.dto;

import com.example.studyroom.security.oauth.KakaoOAuth2Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("DTO - Kakao OAuth 2.0 인증 응답 데이터 테스트")
class KakaoOAuth2ResponseTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @DisplayName("인증 결과를 Map으로 받으면, 카카오 인증 객체로 변환한다.")
    @Test
    void convertMapToKakaoDto() throws JsonProcessingException {
        String serializedResponse = """
                {
                    "id":123456789,
                    "kakao_account": {
                        "name": "홍길동",
                        "birthyear": "1999",
                        "birthday": "1123"
                    } 
                }   
                """;
        Map<String, Object> attributes = mapper.readValue(serializedResponse, new TypeReference<>() {});
        KakaoOAuth2Response result = KakaoOAuth2Response.from(attributes);

        assertThat(result)
                .hasFieldOrPropertyWithValue("id", 123456789L)
                .hasFieldOrPropertyWithValue("kakaoAccount.name", "홍길동")
                .hasFieldOrPropertyWithValue("kakaoAccount.birth", LocalDate.of(1999, 11, 23));
    }
}