package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.domain.LeafBookDomain;
import Recorders.ggogit.domain.leaf.repository.LeafBookRepository;
import Recorders.ggogit.domain.leaf.repository.LeafRepository;
import Recorders.ggogit.domain.leaf.repository.LeafTagMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeafService {

//    @Autowired // TODO: service 수정
//    private TreeRepository treeRepository;

    @Autowired
    private LeafRepository repository;

    @Autowired
    private LeafBookRepository leafBookRepository;

    @Autowired
    private LeafTagMapRepository leafTagMap;

    @Autowired
    private LeafTagMapRepository leafTagMapRepository;

    public Long createLeafBook(LeafBookDomain leafBookDomain) {
        return 1L;
    }
}