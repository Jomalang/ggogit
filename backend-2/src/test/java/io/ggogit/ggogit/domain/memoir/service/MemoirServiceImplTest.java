package io.ggogit.ggogit.domain.memoir.service;

import io.ggogit.ggogit.domain.memoir.entity.Memoir;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.repository.TreeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Rollback;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback
@Profile("test")
class MemoirServiceImplTest {

    @Autowired
    private MemoirService memoirService;
    @Autowired
    private TreeRepository treeRepository;

    @Test
    void regMemoirTest() {
        //given
        Memoir memoir = createMemoir();

        //when
        memoirService.regMemoir(memoir);
        Memoir findMemoir = memoirService.getMemoir(memoir.getId());

        //then
        assertThat(findMemoir.getText()).isEqualTo("testText");
    }

    @Test
    void removeMemoirTest() {
        //given
        Memoir memoir = createMemoir();
        memoirService.regMemoir(memoir);
        //when
        memoirService.removeMemoir(memoir.getId());

        //then
        assertThrows(IllegalArgumentException.class, ()->memoirService.getMemoir(memoir.getId()));
    }

    @Test
    public void modifyMemoirTest () {
        //given
        Memoir memoir = createMemoir();
        memoirService.regMemoir(memoir);
        Memoir newMemoir = new Memoir();
        newMemoir.setText("update");
        newMemoir.setVisibility(true);
        newMemoir.setTitle("update");

        // when
        memoirService.modifyMemoir(newMemoir, memoir.getId());
        // then
        Memoir findMemoir = memoirService.getMemoir(memoir.getId());
        assertThat(findMemoir.getText()).isEqualTo("update");
     }
    
     @Test
     public void saveImageTest() throws IOException {
         //given
         List<String> fileNames = new ArrayList<>();
         fileNames.add("ocean.jpg");

         // when
         memoirService.saveImage(fileNames);
         
         // then
         assertThat(Paths.get("C://ggogit/backend-2/src/main/webapp/uploads/image/memoir/"+fileNames.get(0)).toFile()).exists();
      }
      
      @Test
      public void isMemoirExistTest() {
          //given
          Long treeId1 = 1L;
          Long treeId2 = 2L;
          // when
          Memoir memoir = createMemoir();
          memoirService.regMemoir(memoir); //treeId = 1L로 저장

          // then
          assertThat(memoirService.isMemoirExist(treeId1)).isTrue();
          assertThat(memoirService.isMemoirExist(treeId2)).isFalse();
       }
    

    public Memoir createMemoir(){
        Tree tree = treeRepository.findById(1L).get();
        Memoir memoir = new Memoir();
        memoir.setTree(tree);
        memoir.setTitle("testTitle");
        memoir.setText("testText");
        memoir.setVisibility(true);

        return memoir;
    }
}