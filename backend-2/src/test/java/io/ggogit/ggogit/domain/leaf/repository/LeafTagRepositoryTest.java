package io.ggogit.ggogit.domain.leaf.repository;

import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Profile("test")
@DataJpaTest
class LeafTagRepositoryTest {

    @Autowired
    private LeafTagRepository leafTagRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("Entity Test | LeafTagRepository.findByList")
    void findByList() {
        // given
        String name = "조회 테스트";
        Long memberId = 1000L;
        Member member = memberRepository.findById(memberId).orElseThrow();
        Pageable pageable = PageRequest.of(0, 10);

//        List<LeafTag> leafTags = leafTagRepository.findAll();
//        for (LeafTag leafTag : leafTags) {
//            System.out.println("leafTag = " + leafTag);
//        }

        // when
        Page<LeafTag> leafTagsPage = leafTagRepository.findByMemberAndName(member, name, pageable);
        for (LeafTag leafTag : leafTagsPage) {
            System.out.println("leafTag = " + leafTag);
        }
    }
}