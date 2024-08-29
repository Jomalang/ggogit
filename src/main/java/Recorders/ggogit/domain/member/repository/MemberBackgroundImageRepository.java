package Recorders.ggogit.domain.member.repository;

import Recorders.ggogit.domain.member.entity.MemberBackgroundImage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberBackgroundImageRepository {
    void save(MemberBackgroundImage memberBackgroundImage);
}
