package Recorders.ggogit.domain.member.repository;

import Recorders.ggogit.domain.member.entity.Seed;
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
}
