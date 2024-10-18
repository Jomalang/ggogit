package io.ggogit.ggogit.api.leaf;

import io.ggogit.ggogit.api.leaf.dto.LeafEtcRequest;
import io.ggogit.ggogit.api.leaf.dto.LeafEtcResponse;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.service.LeafEtcService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LeafEtcController {

    private final LeafEtcService leafEtcService;

    @PostMapping("/etc/first/leaves")
    public ResponseEntity<LeafEtcResponse> createFirstEtcLeaf(
            @Valid @RequestBody LeafEtcRequest dto
    ) {
        dto.isValidate();
        Long memberId = 1000L; // TODO: 로그인 정보에서 가져오기
        Leaf leaf = dto.toLeaf();
        Long seedId = dto.getSeedId();
        List<Long> leafTagIds = dto.getTagIds();

        Leaf saved = leafEtcService.createFirstLeafEtc(memberId, leaf, leafTagIds, seedId);

        LeafEtcResponse response = LeafEtcResponse.of(saved, "첫번째 기타 리프 생성 성공");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/etc/leaves/{parentLeafId}")
    public ResponseEntity<LeafEtcResponse> createEtcLeaf(
            @PathVariable Long parentLeafId,
            @Valid @RequestBody LeafEtcRequest dto
    ) {
        dto.isValidate();
        Long memberId = 1000L; // TODO: 로그인 정보에서 가져오기

        if (!leafEtcService.isOwner(memberId, parentLeafId)) {
            throw new IllegalArgumentException("해당 리프에 대한 권한이 없습니다.");
        }

        Leaf leaf = dto.toLeaf();
        List<Long> leafTagIds = dto.getTagIds();

        Leaf saved = leafEtcService.createLeafEtc(memberId, parentLeafId, leaf, leafTagIds);

        LeafEtcResponse response = LeafEtcResponse.of(saved, "기타 리프 생성 성공");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/etc/leaves/{leafId}")
    public ResponseEntity<LeafEtcResponse> updateEtcLeaf(
            @PathVariable Long leafId,
            @Valid @RequestBody LeafEtcRequest dto
    ) {
        dto.isValidate();
        Long memberId = 1000L; // TODO: 로그인 정보에서 가져오기

        if (!leafEtcService.isOwner(memberId, leafId)) {
            throw new IllegalArgumentException("해당 리프에 대한 권한이 없습니다.");
        }

        Leaf leaf = dto.toLeaf();
        List<Long> leafTagIds = dto.getTagIds();

        Leaf saved = leafEtcService.updateLeafEtc(memberId, leafId, leaf, leafTagIds);

        LeafEtcResponse response = LeafEtcResponse.of(saved, "기타 리프 수정 성공");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/etc/leaves/{leafId}")
    public ResponseEntity<LeafEtcResponse> deleteEtcLeaf(
            @PathVariable Long leafId
    ) {

        Long memberId = 1000L; // TODO: 로그인 정보에서 가져오기

        if (!leafEtcService.isOwner(memberId, leafId)) {
            throw new IllegalArgumentException("해당 리프에 대한 권한이 없습니다.");
        }

        leafEtcService.deleteLeafEtc(leafId);
        LeafEtcResponse response = LeafEtcResponse.of(leafId, "기타 리프 삭제 성공");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}