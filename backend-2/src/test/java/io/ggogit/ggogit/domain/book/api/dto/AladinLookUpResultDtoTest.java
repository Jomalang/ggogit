package io.ggogit.ggogit.domain.book.api.dto;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class AladinLookUpResultDtoTest {

    @Test
    @DisplayName("알라딘 검색 결과를 Dto로 변환한다.")
    void apiToDto() throws IOException {
        String jsonContent = Files.readString(Paths.get("api/lookup-api-1.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        AladinLookUpResultDto aladinLookUpResultDto = objectMapper.readValue(jsonContent, AladinLookUpResultDto.class);
        System.out.println(aladinLookUpResultDto.getItem().getFirst().getSubInfo().getItemPage());
    }

}