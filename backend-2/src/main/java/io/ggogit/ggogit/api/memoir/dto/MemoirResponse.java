package io.ggogit.ggogit.api.memoir.dto;

import io.ggogit.ggogit.api.book.dto.BookInfoResponse;
import io.ggogit.ggogit.api.member.dto.MemberInfoResponse;
import io.ggogit.ggogit.api.tree.dto.TreeLightInfoResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemoirResponse {

    private MemoirDto memoirDto;
    private BookInfoResponse bookDto;
    //TODO:TREE DTO 추가하기
    private TreeLightInfoResponse treeDto;
    private MemberInfoResponse memberDto;

    private String message;
    private boolean isOwner;
    public static MemoirResponse of(MemoirDto memoirDto, BookInfoResponse bookDto
    ,TreeLightInfoResponse treeDto
    ,MemberInfoResponse memberDto) {
        return MemoirResponse.builder()
                .memoirDto(memoirDto)
                .bookDto(bookDto)
                .treeDto(treeDto)
                .memberDto(memberDto)
                .build();
    }
    public void ChangeOwnership(boolean ownership) {
        this.isOwner = ownership;
    }
}
