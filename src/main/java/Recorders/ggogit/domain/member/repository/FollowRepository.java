package Recorders.ggogit.domain.member.repository;

import Recorders.ggogit.domain.member.entity.Follow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowRepository {

    void save(Follow follow);

    void delete(Follow follow);

    List<Follow> findAll();

    Follow findByMemberId(String memberId);

}
