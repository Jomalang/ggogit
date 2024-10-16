package io.ggogit.ggogit.domain.member.repository;


public interface MemberProfileImageRepository {

    void save(MemberProfileImage memberProfileImage);

    void update(MemberProfileImage memberProfileImage);

    void delete(MemberProfileImage memberProfileImage);

    List<MemberProfileImage> findAll();

    MemberProfileImage findById(Long id);

    MemberProfileImage findByName(String name);
}
