package Recorders.ggogit.domain.member.repository;

import Recorders.ggogit.domain.member.entity.Seed;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SeedRepository {
    void save(Seed seed);
}
