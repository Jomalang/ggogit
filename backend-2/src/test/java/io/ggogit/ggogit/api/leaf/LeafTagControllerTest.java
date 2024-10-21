package io.ggogit.ggogit.api.leaf;

import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import io.ggogit.ggogit.domain.leaf.repository.LeafTagRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Profile("test")
@SpringBootTest
@AutoConfigureMockMvc
class LeafTagControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LeafTagRepository leafTagRepository;

    @Test
    @DisplayName("리프 태그 등록")
    void register() throws Exception {
        // given
        String validRequest = """
                {
                    "name": "태그 등록 테스트"
                }
                """;

        // when
        ResultActions resultActions = mockMvc.perform(post("/leaf/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequest))
                // then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("태그 생성 성공"))
                .andExpect(jsonPath("$.id").isNumber());

        // then
        
    }

    @Test
    @DisplayName("리프 태그 수정")
    void modify() throws Exception {
        // given
        long tagId = 30000L;
        String validRequest = """
                {
                    "name": "태그 수정"
                }
                """;

        // when
        ResultActions resultActions = mockMvc.perform(put("/leaf/tags/" + tagId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequest))
                // then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("태그 수정 성공"))
                .andExpect(jsonPath("$.id").isNumber());

        // then
        LeafTag leafTag = leafTagRepository.findById(tagId).orElseThrow();
        assertThat(leafTag.getName()).isEqualTo("태그 수정");
    }

    @Test
    @DisplayName("리프 태그 삭제")
    void remove() throws Exception {
        // given
        long tagId = 30001L;

        // when
        mockMvc.perform(delete("/leaf/tags/" + tagId)
                        .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("태그 삭제 성공"))
                .andExpect(jsonPath("$.id").isNumber());

        // then
        assertThat(leafTagRepository.existsById(tagId)).isFalse();
    }

    @Test
    @DisplayName("리프 조회 리스트")
    void list() throws Exception {
        // when
        mockMvc.perform(get("/leaf/tags")
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("search", "태그")
                .queryParam("page", "0")
                .queryParam("size", "20"))

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("태그 목록 조회 성공"));

    }
}