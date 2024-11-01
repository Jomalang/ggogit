package io.ggogit.ggogit.api.tree;

import io.ggogit.ggogit.api.tree.dto.TreeTmpTotalPageResponse;
import io.ggogit.ggogit.api.tree.dto.TreeTmpRequest;
import io.ggogit.ggogit.api.tree.dto.TreeTmpResponse;
import io.ggogit.ggogit.domain.tree.entity.TreeTmp;
import io.ggogit.ggogit.domain.tree.service.TreeTmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/trees")
@RequiredArgsConstructor
public class TreeTmpController {

    private final TreeTmpService treeTmpService;

    @PostMapping
    public ResponseEntity<TreeTmpResponse> createBookTreeTmp(
            @ModelAttribute TreeTmpRequest dto,
            @RequestParam(required = false) MultipartFile image
            // @SessionAttribute Member member
    ) throws IOException {
        TreeTmp treeTmp = dto.toTreeTmp();
        Long memberId = 1000L; // 테스트용 코드
        Long seedId = dto.getSeedId();
        Long bookCategoryId = dto.getBookCategoryId();

        Long treeTmpId = treeTmpService
                .save(treeTmp, memberId, seedId, bookCategoryId, image.getBytes(), image.getOriginalFilename());

        TreeTmpResponse resp = TreeTmpResponse.of(treeTmpId, "도서 트리 임시 저장 성공");

        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @GetMapping("/tmp/{memberId}/total-page")
    public ResponseEntity<TreeTmpTotalPageResponse> getTotalPage(
            @PathVariable Long memberId
    ) {
        TreeTmp treeTmp = treeTmpService.getTreeTmp(memberId);
        TreeTmpTotalPageResponse response = TreeTmpTotalPageResponse.of(treeTmp);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}