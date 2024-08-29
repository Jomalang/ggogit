package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.domain.LeafTagDomain;
import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.repository.LeafTagMapRepository;
import Recorders.ggogit.domain.leaf.repository.LeafTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeafTagService {

    @Autowired
    private LeafTagRepository repository;

    @Autowired
    private LeafTagMapRepository leafTagMapRepository;

    /**
     * 리프 태그 생성
     * */
    public void createLeafTag(LeafTagDomain leafTagDomain) {
        LeafTag leafTag = leafTagDomain.toEntity();
        repository.save(leafTag);
    }
}
