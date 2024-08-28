package Recorders.ggogit.domain.tree.service;

import Recorders.ggogit.domain.tree.entity.Tree;
import Recorders.ggogit.domain.tree.repository.TreeRegRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreeRegRowServiceImpl implements TreeRegRowService {
    @Autowired
    private TreeRegRepository repo;

    @Override
    public void addTreeRegRow(Tree tree) {
        repo.addTreeRow(tree);
    }
}
