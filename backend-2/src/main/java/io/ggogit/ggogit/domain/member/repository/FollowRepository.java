package io.ggogit.ggogit.domain.member.repository;

public interface FollowRepository {

    void save(Follow follow);

    void delete(Follow follow);

    List<Follow> findAll();

    Follow findByMemberId(String memberId);

}
