package Recorders.ggogit.domain.tree.service;

import Recorders.ggogit.domain.tree.entity.Tree;
import org.springframework.stereotype.Service;

@Service
public interface TreeRegRowService {

    void addTreeRegRow(Tree tree);
}
