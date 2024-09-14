package Recorders.ggogit.domain.tree.service;

import Recorders.ggogit.domain.tree.entity.Seed;

import java.util.List;

public interface SeedService {

    boolean isBook(String name);
    boolean isBook(Long id);

    boolean isBookById(Long name);

    boolean contains(String seed);

    List<Seed> getSeeds();

    Seed get(String name);

    Seed getByDiscription(String type);
}