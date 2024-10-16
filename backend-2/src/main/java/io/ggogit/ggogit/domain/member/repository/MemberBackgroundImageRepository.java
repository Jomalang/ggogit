package io.ggogit.ggogit.domain.member.repository;

public interface MemberBackgroundImageRepository {

    void save(MemberBackgroundImage memberBackgroundImage);

    void update(MemberBackgroundImage memberBackgroundImage);

    void delete(MemberBackgroundImage memberBackgroundImage);

    List<MemberBackgroundImage> findAll();

    MemberBackgroundImage findById(Long id);

    MemberBackgroundImage findByName(String name);

}
