package io.ggogit.ggogit.domain.memoir.service;

import io.ggogit.ggogit.domain.memoir.entity.Memoir;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

public interface MemoirService {

    /**
     * @param memoir, treeId 회고록 엔티티와 연관관계에 있는 treeId를 받습니다.
     * @return memoiId
     * memoir엔티티를 영속화합니다.
     */
    Long regMemoir(Memoir memoir, Long TreeId);

    /**
     * @param memoirId 회고록의 식별자입니다.
     * @return void 반환값은 void입니다.
     * memoirId를 통해 논리적 삭제를 진행합니다.
     * 논리적 삭제를 위한 SQL은 memoir 엔티티에 정의되어 있습니다.
     */
    void removeMemoir(Long memoirId);

    /**
     * @param newMemoir, memoirId
     * @return void
     * 수정할 회고록객체와 회고록의 식별자를 인자로 받고, 회고록을 수정합니다.
     */
    void modifyMemoir(Memoir newMemoir, Long memoirId);

    /**
     * @param memoirId
     * @return Memoir엔티티
     * 회고록 식별자로 조회한 회고록을 반환합니다.
     */
    Memoir getMemoir(Long memoirId);

    /**
     * @param fileNames
     * @throws IOException
     */
    void saveImage(List<String> fileNames) throws IOException;


    /**
     * @param treeId
     * @return boolean
     * 이미 회고록이 생성되어있으면 true를 반환합니다.
     */
    boolean isMemoirExist(Long treeId);

    /**
     *
     * @param memberId
     * @param memoirId
     * @return
     * 회고록의 소유자인지 검사합니다.
     */
    boolean isOwner(Long memberId, Long memoirId);

//    List<MemoirBookView> getMemoirCards(Long memberId);
}