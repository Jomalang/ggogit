package Recorders.ggogit.domain.tree.repository;

import Recorders.ggogit.domain.tree.view.TreeSaveTmpView;

public interface TreeSaveTmpRepository {

    void save(TreeSaveTmpView view); // id 값 반환

    TreeSaveTmpView find(Long memberId);

    Boolean isEmpty(Long memberId);

    boolean delete(Long id);

}
