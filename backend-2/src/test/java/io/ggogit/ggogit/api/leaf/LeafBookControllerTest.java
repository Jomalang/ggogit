package io.ggogit.ggogit.api.leaf;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.entity.BookCategory;
import io.ggogit.ggogit.domain.book.repository.BookCategoryRepository;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.repository.LeafRepository;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.repository.MemberRepository;
import io.ggogit.ggogit.domain.tree.entity.Seed;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.entity.TreeSaveTmp;
import io.ggogit.ggogit.domain.tree.repository.SeedRepository;
import io.ggogit.ggogit.domain.tree.repository.TreeSaveTmpRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Profile("test")
@SpringBootTest
@AutoConfigureMockMvc
class LeafBookControllerTest {

    @Value("${file.upload-dir}")
    private String uploadDir;
    @Value("${file.tmp-dir}")
    private String tmpDir;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TreeSaveTmpRepository treeSaveTmpRepository;
    @Autowired
    private BookCategoryRepository bookCategoryRepository;
    @Autowired
    private SeedRepository seedRepository;
    @Autowired
    private LeafRepository leafRepository;

    @Test
    @DisplayName("첫번째 도서 리프 생성")
    @Transactional
    void createFirstBookLeaf() throws Exception {
        // given
        Member member = memberRepository.findById(1000L).orElseThrow();
        Seed seed = seedRepository.findById(1L).orElseThrow();
        BookCategory bookCategory = bookCategoryRepository.findById(1L).orElseThrow();
        String imageFilePath = createImageFile("firstBookTest.png", tmpDir);
        TreeSaveTmp treeSaveTmp = TreeSaveTmp.builder()
                .member(member)
                .book(null)
                .bookCategory(bookCategory)
                .seed(seed)
                .bookTitle("나의 첫번째 도서 제목")
                .treeTitle("나의 첫번째 트리 제목")
                .visibility(true)
                .description("나의 첫번째 도서 설명")
                .author("나의 첫번째 도서 저자")
                .imageFile(imageFilePath)
                .publisher("나의 첫번째 도서 출판사")
                .totalPage(100)
                .build();

        treeSaveTmpRepository.save(treeSaveTmp);

        String validRequest = """
                {
                    "startPage": 1,
                    "endPage": 100,
                    "tagIds": [10001, 10002, 10003],
                    "title": "나의 첫번째 리프 제목 기록 성공",
                    "content": "나의 첫번째 리프 내용 성공",
                    "visibility": true
                }
                """;

        // when
        ResultActions resultActions = mockMvc.perform(post("/api/v1/book/first/leafs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequest))
                // then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("첫번째 도서 리프 생성 성공"))
                .andExpect(jsonPath("$.leafId").isNumber());

        // leafId 값 추출
        String responseJson = resultActions.andReturn().getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(responseJson);
        Long extractedLeafId = jsonResponse.path("leafId").asLong();

        // end
        Leaf leaf = leafRepository.findById(extractedLeafId).orElseThrow();
        Tree tree = leaf.getTree();
        Book book = tree.getBook();
        removeImageFile(book.getImageFile(), "book"); // 이미지 제거
    }

    private boolean removeImageFile(String fileName, String folder) {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath,  uploadDir + File.separator + "image" + File.separator + folder + File.separator + fileName);
        System.out.println("file = " + file);
        return file.delete();
    }

    private String createImageFile(String fileName, String folder) {
        int width = 200;  // 이미지 너비
        int height = 200; // 이미지 높이

        // 빈 이미지 생성 (흰색 배경)
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, 0xFFFFFFFF); // 흰색
            }
        }

        // 이미지 파일로 저장
        try {
            File outputfile = new File(folder, fileName);
            ImageIO.write(image, "png", outputfile);
            String projectPath = System.getProperty("user.dir");
            return projectPath + File.separator + outputfile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("이미지 파일 생성 실패");
    }


    @Test
    @DisplayName("도서 리프 생성")
    void createBookLeaf() throws Exception {
        // given
        String validRequest = """
                {
                    "startPage": 1,
                    "endPage": 100,
                    "tagIds": [10001, 10002, 10003],
                    "title": "리프 제목 기록 성공",
                    "content": "리프 내용 성공",
                    "visibility": true
                }
                """;
        long parentLeafId = 1L;

        // when
        ResultActions resultActions = mockMvc.perform(post("/api/v1/book/leafs/" + parentLeafId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequest))
                // then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("도서 리프 생성 성공"))
                .andExpect(jsonPath("$.leafId").isNumber());



        // then
    }
}