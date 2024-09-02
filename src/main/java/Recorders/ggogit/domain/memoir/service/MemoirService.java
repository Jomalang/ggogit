package Recorders.ggogit.domain.memoir.service;


import Recorders.ggogit.web.memoir.MemoirForm;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;

@Transactional
public interface MemoirService {

    /**
     * @param memoirForm, id
     * @return 저장한 회고록의 id를 반환합니다.
     * memoirRegForm를 인자로 받습니다. Memoir객체를 생성하고 값을 할당합니다.
     */
    public long regMemoir(MemoirForm memoirForm);

    /**
     * @param id
     * @return 삭제한 회고록의 id를 반환합니다.
     * 회고록의 id를 입력받아 해당 회고록을 DB에서 찾고 삭제합니다.
     */
    public long removeMemoir(long id);

    /**
     * @param memoirForm, id
     * @return 수정한 회고록의 id를 반환합니다.
     * memoirRegForm을 인자로 받습니다. 기존 Memoir객체를 id로 찾아오고, 값을 새로 할당합니다.
     */
    public long modifyMemoir(MemoirForm memoirForm, long id);

    /**
     * @param treeId
     * @return treeId로 조회한 회고록을 반환합니다.
     */
    public MemoirForm getMemoir(long treeId);

    /**
     * @param memberId
     * @return memberId로 조회한 회고록의 목록을 반환합니다.
     */
    public List<MemoirForm> getMemoirs(long memberId);
}
