package io.ggogit.ggogit.api.memoir.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemoirResponse {

    private MemoirDto memoirdto;
    private BookDto bookDto;
    private TreeDto treeDto;
    private String message;

    public static of(MemoirDto, BookDto,)
}
