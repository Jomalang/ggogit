package io.ggogit.ggogit.domain.memoir.service;

import io.ggogit.ggogit.domain.memoir.entity.Memoir;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Transactional
public interface MemoirService {

    /**
     * @param memoir, id
     * @return 저장한 회고록의 id를 반환합니다.
     */
    long regMemoir(Memoir memoir);

    /**
     * @param id
     * @return 삭제한 회고록의 id를 반환합니다.
     * 회고록의 id를 입력받아 해당 회고록을 DB에서 찾고 삭제합니다.
     */
    long removeMemoir(long treeId);

    /**
     * @param newMemoir, id //수정할 memoir객체와 이전 Tree의 id를 인자로 받습니다.
     * @return void입니다.
     */
    void modifyMemoir(Memoir newMemoir, long treeId);

    /**
     * @param treeId
     * @return treeId로 조회한 회고록을 반환합니다.
     */
    Memoir getMemoir(long treeId);

    void imageSave(List<String> fileNames) throws IOException;

//    List<MemoirBookView> getMemoirCards(Long memberId);
}