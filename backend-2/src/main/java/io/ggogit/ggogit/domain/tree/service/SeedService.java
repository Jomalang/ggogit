package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.domain.tree.entity.Seed;

import java.util.List;

public interface SeedService {

    boolean isBook(String name);
    boolean isBook(Long id);

    boolean isBookById(Long id);

    boolean contains(String seed);

    List<Seed> getSeeds();

    Seed getByEngName(String name);

    Seed get(Long id);

    Seed getByDiscription(String type);


}