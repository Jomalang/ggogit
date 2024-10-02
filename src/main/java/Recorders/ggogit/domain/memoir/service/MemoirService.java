package Recorders.ggogit.domain.memoir.service;


import Recorders.ggogit.domain.memoir.entity.Memoir;
import Recorders.ggogit.domain.memoir.vIew.MemoirBookView;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Transactional
public interface MemoirService {

    /**
     * @param memoir, id
     * @return 저장한 회고록의 id를 반환합니다.
     */
    public long regMemoir(Memoir memoir);

    /**
     * @param id
     * @return 삭제한 회고록의 id를 반환합니다.
     * 회고록의 id를 입력받아 해당 회고록을 DB에서 찾고 삭제합니다.
     */
    public long removeMemoir(long treeId);

    /**
     * @param newMemoir, id //수정할 memoir객체와 이전 Tree의 id를 인자로 받습니다.
     * @return void입니다.
     */
    public void modifyMemoir(Memoir newMemoir, long treeId);

    /**
     * @param treeId
     * @return treeId로 조회한 회고록을 반환합니다.
     */
    public Memoir getMemoir(long treeId);

    public void imageSave(List<String> fileNames) throws IOException;

    public List<MemoirBookView> getMemoirCards(Long memberId);
}
