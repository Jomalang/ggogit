package io.ggogit.ggogit.domain.memoir.service;

import io.ggogit.ggogit.api.memoir.dto.MemoirCardDtoResponse;
import io.ggogit.ggogit.api.memoir.dto.MemoirCardDtoResponseList;
import io.ggogit.ggogit.domain.memoir.repository.MemoirRepository;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.repository.TreeRepository;
import lombok.RequiredArgsConstructor;
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

    public MemoirCardDtoResponseList getMemoirCardDtoResponseList(Long memberId) {
        int page = 0;
        int size = 10;
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(page, size, sort);

        List<Tree> trees = treeRepository.findTreeByMemberIdFetch(memberId, pageable).getContent();
        List<MemoirCardDtoResponse> memoirCardDtoResponses = trees.stream().filter(tree -> tree.getMemoir() != null)
                .map(tree -> {
                    MemoirCardDtoResponse dto = MemoirCardDtoResponse.of(tree.getMemoir(), tree);
                    long leafNum = tree.getLeaf().stream().count();
                    dto.setLeafCount(leafNum);
                    return dto;
                }
                ).toList();

        return MemoirCardDtoResponseList.of(memoirCardDtoResponses);
    }
}
