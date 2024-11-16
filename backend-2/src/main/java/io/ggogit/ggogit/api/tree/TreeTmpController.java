package io.ggogit.ggogit.api.tree;

import io.ggogit.ggogit.api.tree.dto.*;
import io.ggogit.ggogit.domain.member.security.CustomUserDetails;
import io.ggogit.ggogit.domain.tree.entity.TreeTmp;
import io.ggogit.ggogit.domain.tree.service.TreeTmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @ModelAttribute TreeTmpRequest dto,
            @RequestParam(required = false) MultipartFile image
    ) throws IOException {

        TreeTmp treeTmp = dto.toTreeTmp();
        Long memberId = userDetails.getId();
        Long seedId = dto.getSeedId();
        Long bookCategoryId = dto.getBookCategoryId();

        Long treeTmpId = treeTmpService
                .save(treeTmp, memberId, seedId, bookCategoryId, image.getBytes(), image.getOriginalFilename());

        TreeTmpResponse resp = TreeTmpResponse.of(treeTmpId, "도서 트리 임시 저장 성공", HttpStatus.CREATED.value());

        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PostMapping("/etc")
    public ResponseEntity<TreeTmpResponse> createEtcTreeTmp(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @ModelAttribute TreeEtcTmpRequest dto,
            @RequestParam(required = false) MultipartFile image
    ) throws IOException {

        TreeTmp treeTmp = dto.toTreeTmp();
        Long memberId = userDetails.getId();
        Long seedId = dto.getSeedId();

        Long treeTmpId = treeTmpService
                .save(treeTmp, memberId, seedId, image.getBytes(), image.getOriginalFilename());

        TreeTmpResponse resp = TreeTmpResponse.of(treeTmpId, "기타 트리 임시 저장 성공", HttpStatus.CREATED.value());

        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PostMapping("/auto")
    public ResponseEntity<TreeTmpResponse> createAutoTreeTmp(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody BookAutoTreeTmpRequest dto
    ) {
        TreeTmp treeTmp = dto.toTreeTmp();
        Long bookId = dto.getBookId();

        Long treeTmpId = treeTmpService.save(treeTmp, userDetails.getId(), bookId);

        TreeTmpResponse resp = TreeTmpResponse.of(treeTmpId, "도서 선택 트리 임시 저장 성공", HttpStatus.CREATED.value());

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