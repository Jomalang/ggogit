package Recorders.ggogit.domain.member.repository;

import Recorders.ggogit.domain.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {
    void save(Member member);
}
