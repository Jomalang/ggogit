package Recorders.ggogit.domain.member.repository;

import Recorders.ggogit.domain.member.entity.MemberBackgroundImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberBackgroundImageRepository {

    void save(MemberBackgroundImage memberBackgroundImage);

    void update(MemberBackgroundImage memberBackgroundImage);

    void delete(MemberBackgroundImage memberBackgroundImage);

    List<MemberBackgroundImage> findAll();

    MemberBackgroundImage findById(Long id);

    MemberBackgroundImage findByName(String name);

}
