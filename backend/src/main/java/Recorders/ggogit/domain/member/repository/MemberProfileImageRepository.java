package Recorders.ggogit.domain.member.repository;

import Recorders.ggogit.domain.member.entity.MemberProfileImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberProfileImageRepository {

    void save(MemberProfileImage memberProfileImage);

    void update(MemberProfileImage memberProfileImage);

    void delete(MemberProfileImage memberProfileImage);

    List<MemberProfileImage> findAll();

    MemberProfileImage findById(Long id);

    MemberProfileImage findByName(String name);
}
