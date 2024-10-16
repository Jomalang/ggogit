package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.domain.tree.entity.Seed;

import java.util.List;

public interface SeedService {

    List<Seed> list();

    Seed getByEngName(String name);

    Seed get(Long id);

}