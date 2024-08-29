package Recorders.ggogit.domain.member.repository;

import Recorders.ggogit.domain.member.entity.MemberProfileImage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberProfileImageRepository {
    void save(MemberProfileImage memberProfileImage);
}
