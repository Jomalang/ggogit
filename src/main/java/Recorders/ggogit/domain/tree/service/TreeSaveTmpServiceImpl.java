package Recorders.ggogit.domain.tree.service;

import Recorders.ggogit.domain.tree.entity.TreeSaveTmp;
import Recorders.ggogit.domain.tree.repository.TreeSaveTmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class TreeSaveTmpServiceImpl implements TreeSaveTmpService {

    @Autowired
    private TreeSaveTmpRepository treeSaveTmpRepository;

    @Override
    public TreeSaveTmp get(Long memberId) {
        return Optional.ofNullable(treeSaveTmpRepository.findById(memberId))
                .orElseThrow(() -> new IllegalArgumentException("해당하는 TreeSaveTmp가 없습니다."));
    }
}
