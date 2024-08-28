package Recorders.ggogit.domain.leaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeafService {

    @Autowired
    private LeafRepository leafRepository;

    public void save(Leaf leaf) {
        leafRepository.save(leaf);
    }
}
