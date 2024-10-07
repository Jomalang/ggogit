package Recorders.ggogit.web.leaf.form;

import Recorders.ggogit.web.leaf.LeafController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LeafController.class)
class LeafBookFirstFromTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("LeafBookFirstFrom 데이터 유효성 검사 | 성공")
    void getFirstReg() throws Exception {
        mockMvc.perform(post("/leaf/first/reg")
                .param("seed", "1")
                .param("startPage", "100")
                .param("endPage", "101")
                .param("tagIds", "1")
                .param("tagIds", "2")
                .param("tagIds", "3")
                .param("title", "title")
                .param("content", "content")
                .param("visibility", "true")
        ).andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("LeafBookFirstFrom 데이터 유효성 검사 | 실패 - 시작 페이지 미입력")
    void getFirstReg_fail_startPage() throws Exception {
        mockMvc.perform(post("/leaf/first/reg")
                .param("seed", "1")
                .param("endPage", "101")
                .param("tagIds", "1")
                .param("tagIds", "2")
                .param("tagIds", "3")
                .param("title", "title")
                .param("content", "content")
                .param("visibility", "true")
        ).andExpect(status().isOk());
    }
}