package Recorders.ggogit.domain.tree.repository;

import Recorders.ggogit.domain.tree.entity.Seed;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SeedRepository {

    void save(Seed seed);

    void update(Seed seed);

    void delete(Seed seed);

    List<Seed> findAll();

    Seed findById(Long id);

    Seed findByName(String name);

    Seed findByDescription(String description);
}