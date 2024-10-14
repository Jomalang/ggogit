package io.ggogit.ggogit.domain.member.repository;

import io.ggogit.ggogit.domain.member.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Profile("test")
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void test() {
        // given
        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            System.out.println(member);
        }
    }
}