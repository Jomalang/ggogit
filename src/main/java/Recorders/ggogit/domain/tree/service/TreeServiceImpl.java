package Recorders.ggogit.domain.tree.service;

import Recorders.ggogit.domain.tree.repository.TreeRepository;
import Recorders.ggogit.domain.tree.view.Vm_FindTreeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private Vm_FindTreeInfo vmFindTreeInfo;


}
