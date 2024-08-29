package Recorders.ggogit.domain.member.repository;

import Recorders.ggogit.domain.member.entity.Follow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowRepository {
    void save(Follow follow);
}
