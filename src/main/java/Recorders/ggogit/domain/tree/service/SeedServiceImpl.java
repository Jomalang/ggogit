package Recorders.ggogit.domain.tree.service;

import Recorders.ggogit.domain.tree.entity.Seed;
import Recorders.ggogit.domain.tree.repository.SeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeedServiceImpl implements SeedService {

    @Autowired
    private SeedRepository seedRepository;

    @Override
    public boolean isBook(String name) {
        Seed seed = seedRepository.findByName(name);
        return seed != null && seed.getName().equals("book");
    }

    @Override
    public boolean isBook(Long id) {
        Seed seed = seedRepository.findById(id);
        return seed != null && seed.getName().equals("book");
    }

    @Override
    public boolean isBookById(Long name) {
        Seed seed = seedRepository.findById(name);
        return seed != null && seed.getName().equals("book");
    }

    @Override
    public boolean contains(String name) {
        Seed seed = seedRepository.findByName(name);
        return seed != null; // 여기 확인중 < 데이터 확인 후 Seed 생성 해야함
    }

    @Override
    public List<Seed> getSeeds() {
        return seedRepository.findAll();
    }

    @Override
    public Seed get(String name) {
        return Optional.ofNullable(seedRepository.findByName(name))
                .orElseThrow(() -> new IllegalArgumentException("해당하는 Seed가 없습니다."));
    }

    @Override
    public Seed get(Long id) {
        return Optional.ofNullable(seedRepository.findById(id))
                .orElseThrow(() -> new IllegalArgumentException("해당하는 Seed가 없습니다."));
    }

    @Override
    public Seed getByDiscription(String description) {
        return Optional.ofNullable(seedRepository.findByDescription(description))
                .orElseThrow(() -> new IllegalArgumentException("해당하는 Seed가 없습니다."));
    }
}