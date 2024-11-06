package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.api.leaf.dto.BookLeafEditResponse;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafBook;

import java.util.List;

public interface LeafBookService {

    /**
     * 첫번쨰 리프 생성
     * */
    LeafBook createFirstLeafBook(Long memberId, Leaf leaf, LeafBook leafBook, List<Long> leafTagIds);

    /**
     * 리프 생성
     * */
    LeafBook createLeafBook(Long memberId, Long parentLeafId, Leaf leaf, LeafBook leafBook, List<Long> leafTagIds);

    /**
     * 리프 수정
     * */
    LeafBook updateLeafBook(Long memberId, Long leafId, Leaf toLeaf, LeafBook toLeafBook, List<Long> toLeafTagIds);

    /**
     * 소유권 확인
     * */
    boolean isOwner(Long memberId, Long leafId);

    /**
     * 리프 삭제
     * */
    void deleteLeafBook(Long leafId);

    /**
     * 리프 수정 화면 조회
     * */
    BookLeafEditResponse getEdit(Long leafId);
}