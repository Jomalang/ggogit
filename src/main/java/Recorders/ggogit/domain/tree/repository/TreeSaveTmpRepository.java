package Recorders.ggogit.domain.tree.repository;

import Recorders.ggogit.web.tree.form.TreeEtcSaveTmpForm;
import Recorders.ggogit.web.tree.form.TreeSaveTmpForm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TreeSaveTmpRepository {

    void save(TreeSaveTmpForm form); // id 값 반환

    TreeSaveTmpForm find(Long memberId);

    Boolean isEmpty(Long memberId);

    void deleteByMemberId(Long memberId);

    void saveEtc(TreeEtcSaveTmpForm form);
}
