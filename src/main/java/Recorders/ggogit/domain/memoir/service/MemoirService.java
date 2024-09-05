package Recorders.ggogit.domain.memoir.service;


import Recorders.ggogit.domain.memoir.entity.Memoir;
import Recorders.ggogit.web.memoir.MemoirForm;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

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
    public long removeMemoir(long id);

    /**
     * @param memoir, id
     * @return 수정한 회고록의 id를 반환합니다.
     */
    public long modifyMemoir(Memoir memoir, long id);

    /**
     * @param treeId
     * @return treeId로 조회한 회고록을 반환합니다.
     */
    public Memoir getMemoir(long treeId);

    /**
     * @param memberId
     * @return memberId로 조회한 회고록의 목록을 반환합니다.
     */
    public List<Memoir> getMemoirs(long memberId);
}
