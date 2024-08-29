package Recorders.ggogit.web.tree;

import Recorders.ggogit.domain.tree.entity.Tree;
import Recorders.ggogit.domain.tree.service.TreeRegRowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test/tree")
@Controller
public class TreeRegController {

    @Autowired
    public TreeRegRowServiceImpl treeRegRowService;

    @GetMapping("/reg")
    void setTreeReg() {

        Tree tree;

        for(int i = 1; i < 11; ++i) {

            tree = Tree.builder()
                    .id(i + 10)
                    .memberId(1)
                    .seedId(1)
                    .title("tree title" + i + "test")
                    .description("tree" + i + "description")
                    .bookMarkCountNum(0)
                    .visibility(true)
                    .build();
            treeRegRowService.addTreeRow(tree);
        }

    }

}
