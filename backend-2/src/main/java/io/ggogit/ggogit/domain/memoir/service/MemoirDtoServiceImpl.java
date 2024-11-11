package io.ggogit.ggogit.domain.memoir.service;

import io.ggogit.ggogit.api.memoir.dto.MemoirBookCardDtoResponse;
import io.ggogit.ggogit.api.memoir.dto.MemoirBookCardDtoResponseList;
import io.ggogit.ggogit.api.memoir.dto.MemoirCardDtoResponse;
import io.ggogit.ggogit.domain.memoir.entity.Memoir;
import io.ggogit.ggogit.domain.memoir.repository.MemoirRepository;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.repository.TreeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoirDtoServiceImpl implements MemoirDtoService {

    private final MemoirRepository memoirRepository;
    private final TreeRepository treeRepository;

    public MemoirBookCardDtoResponseList getMemoirBookCardDtoResponseList(Long memberId) {
        int offset = 0;
        int limit = 10;
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(offset, limit, sort);

        List<Tree> trees = treeRepository.findTreeByMemberIdFetch(memberId, pageable).getContent();
        List<MemoirBookCardDtoResponse> memoirBookCardDtoRespons = trees.stream().filter(tree -> tree.getMemoir() != null)
                .map(tree -> {
                    MemoirBookCardDtoResponse dto = MemoirBookCardDtoResponse.of(tree.getMemoir(), tree);
                    long leafNum = tree.getLeaf().stream().count();
                    dto.setLeafCount(leafNum);
                    return dto;
                }
                ).toList();

        return MemoirBookCardDtoResponseList.of(memoirBookCardDtoRespons);
    }

    @Override
    public MemoirCardDtoResponse getMemoirCardDtoResponse(Long bookId, int page, int size) {
        Pageable pageable = PageRequest.of((page-1), size, Sort.by(Sort.Direction.DESC, "updateTime"));
        Page<Tree> trees = treeRepository.findTreeByBookIdFetch(bookId, pageable);

        return MemoirCardDtoResponse.of(trees);
    }
}
