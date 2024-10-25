package io.ggogit.ggogit.api.leaf;

import io.ggogit.ggogit.api.leaf.dto.LeafTagDetailResponse;
import io.ggogit.ggogit.api.leaf.dto.LeafTagListResponse;
import io.ggogit.ggogit.api.leaf.dto.LeafTagRequest;
import io.ggogit.ggogit.api.leaf.dto.LeafTagResponse;
import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import io.ggogit.ggogit.domain.leaf.service.LeafTagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("tags")
public class LeafTagController {

    private final LeafTagService leafTagService;

    // 생성
    @PostMapping
    public ResponseEntity<LeafTagResponse> register(
        @Valid @RequestBody LeafTagRequest dto
    ) {
        Long memberId = 1000L; // TODO: 로그인 정보에서 가져오기
        LeafTag leafTag = leafTagService.register(memberId, dto.getName());
        LeafTagResponse response = LeafTagResponse.of(leafTag, "태그 생성 성공");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 수정
    @PutMapping("/{tagId}")
    public ResponseEntity<LeafTagResponse> modify(
        @PathVariable Long tagId,
        @Valid @RequestBody LeafTagRequest dto
    ) {
        Long memberId = 1000L; // TODO: 로그인 정보에서 가져오기

        if (!leafTagService.isOwner(memberId, tagId)) {
            throw new IllegalArgumentException("해당 태그에 대한 권한이 없습니다.");
        }

        // 기존에 존재하는지 확인
        if (leafTagService.isExist(memberId, dto.getName())) {
            LeafTagResponse response = LeafTagResponse.of(tagId, "이미 존재하는 태그입니다.");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        LeafTag leafTag = leafTagService.modify(memberId, tagId, dto.getName());
        LeafTagResponse response = LeafTagResponse.of(leafTag, "태그 수정 성공");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 삭제
    @DeleteMapping("/{tagId}")
    public ResponseEntity<LeafTagResponse> remove(
            @PathVariable Long tagId
    ) {
        Long memberId = 1000L; // TODO: 로그인 정보에서 가져오기

        if (!leafTagService.isOwner(memberId, tagId)) {
            throw new IllegalArgumentException("해당 태그에 대한 권한이 없습니다.");
        }

        leafTagService.remove(memberId, tagId);
        LeafTagResponse response = LeafTagResponse.of(tagId, "태그 삭제 성공");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{tagId}")
    public ResponseEntity<LeafTagDetailResponse> get(
            @PathVariable Long tagId
    ) {
        Long memberId = 1000L; // TODO: 로그인 정보에서 가져오기

        if (!leafTagService.isOwner(memberId, tagId)) {
            throw new IllegalArgumentException("해당 태그에 대한 권한이 없습니다.");
        }

        LeafTag leafTag = leafTagService.detail(tagId);
        LeafTagDetailResponse response = LeafTagDetailResponse.of(leafTag);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 조회
    @GetMapping
    public ResponseEntity<LeafTagListResponse> list(
            @RequestParam(value = "s", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        Long memberId = 1000L; // TODO: 로그인 정보에서 가져오기
        Page<LeafTag> leafTags = leafTagService.list(memberId, search, page, size);
        LeafTagListResponse response = LeafTagListResponse.of(leafTags, "태그 목록 조회 성공");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}