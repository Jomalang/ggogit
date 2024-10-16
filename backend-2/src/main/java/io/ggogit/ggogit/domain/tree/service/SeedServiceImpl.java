package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.domain.tree.entity.Seed;
import io.ggogit.ggogit.domain.tree.repository.SeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeedServiceImpl implements SeedService {

    private final SeedRepository seedRepository;

    @Override
    public List<Seed> list() { return seedRepository.findAll(); }

    @Override
    public Seed getByEngName(String name) {
        return Optional.ofNullable(seedRepository.getSeedByKorName(name))
                .orElseThrow(() -> new IllegalArgumentException("해당하는 Seed가 없습니다."));
    }

    @Override
    public Seed get(Long id) {
        return Optional.ofNullable(seedRepository.getSeedById(id))
                .orElseThrow(() -> new IllegalArgumentException("해당하는 Seed가 없습니다."));
    }

}
