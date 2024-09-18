package Recorders.ggogit.domain.member.repository;

import Recorders.ggogit.domain.member.entity.Member;
import Recorders.ggogit.domain.member.view.MemberImageView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberRepository {

    void save(Member member);

    void update(Member member);

    void delete(Member member);

    List<Member> findAll();

    Member findById(Long id);

    Member findByEmail(String Email);

    Member findByNickname(String nickname);

    Member findByUsername(String username);

    MemberImageView getMemberImageView(Long memberId);
}
