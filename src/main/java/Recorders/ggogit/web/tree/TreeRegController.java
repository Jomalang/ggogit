package Recorders.ggogit.web.tree;

import Recorders.ggogit.domain.tree.entity.Tree;
import Recorders.ggogit.domain.tree.service.TreeRegRowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TreeRegController {

    @Autowired
    public static TreeRegRowServiceImpl treeRegRowService;

    public static void main(String[] args){

        Tree tree = new Tree();

        for(int i=1; i<10; ++i){

            treeRegRowService.addTreeRegRow(Tree(i,1,1,"i","0",0,true));
        }



    }

}
