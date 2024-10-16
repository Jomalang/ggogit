package io.ggogit.ggogit.domain.book.api.dto;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class AladinSearchResultDtoTest {

    @Test
    @DisplayName("알라딘 검색 결과를 Dto로 변환한다.")
    void apiToDto() throws IOException {
        String jsonContent = Files.readString(Paths.get("api/search-api.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        AladinSearchResultDto book = objectMapper.readValue(jsonContent, AladinSearchResultDto.class);
        for (ApiBookDto item : book.getItem()) {
            System.out.println(item);
        }
    }
}