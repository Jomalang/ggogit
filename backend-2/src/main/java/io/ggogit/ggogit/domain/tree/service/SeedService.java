package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.domain.tree.entity.Seed;

import java.util.List;
import java.util.Optional;

public interface SeedService {

    List<Seed> list();

    Seed getByEngName(String name);

    Optional<Seed> get(Long id);

}