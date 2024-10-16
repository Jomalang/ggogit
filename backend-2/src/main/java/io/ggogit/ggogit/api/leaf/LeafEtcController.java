package io.ggogit.ggogit.api.leaf;

import io.ggogit.ggogit.api.leaf.dto.EtcLeafRequest;
import io.ggogit.ggogit.api.leaf.dto.EtcLeafResponse;
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

    @PostMapping("/etc/first/leafs")
    public ResponseEntity<EtcLeafResponse> createFirstEtcLeaf(
            @Valid @RequestBody EtcLeafRequest dto
    ) {
        dto.isValidate();
        Long memberId = 1000L; // TODO: 로그인 정보에서 가져오기
        Leaf leaf = dto.toLeaf();
        Long seedId = dto.getSeedId();
        List<Long> leafTagIds = dto.getTagIds();

        Leaf saved = leafEtcService.createFirstLeafEtc(memberId, leaf, leafTagIds, seedId);

        EtcLeafResponse response = EtcLeafResponse.of(saved, "첫번째 기타 리프 생성 성공");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/etc/leafs/{parentLeafId}")
    public ResponseEntity<EtcLeafResponse> createEtcLeaf(
            @PathVariable Long parentLeafId,
            @Valid @RequestBody EtcLeafRequest dto
    ) {
        dto.isValidate();
        Long memberId = 1000L; // TODO: 로그인 정보에서 가져오기

        if (!leafEtcService.isOwner(memberId, parentLeafId)) {
            throw new IllegalArgumentException("해당 리프에 대한 권한이 없습니다.");
        }

        Leaf leaf = dto.toLeaf();
        List<Long> leafTagIds = dto.getTagIds();

        Leaf saved = leafEtcService.createLeafEtc(memberId, parentLeafId, leaf, leafTagIds);

        EtcLeafResponse response = EtcLeafResponse.of(saved, "기타 리프 생성 성공");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/etc/leafs/{leafId}")
    public ResponseEntity<EtcLeafResponse> updateEtcLeaf(
            @PathVariable Long leafId,
            @Valid @RequestBody EtcLeafRequest dto
    ) {
        dto.isValidate();
        Long memberId = 1000L; // TODO: 로그인 정보에서 가져오기

        if (!leafEtcService.isOwner(memberId, leafId)) {
            throw new IllegalArgumentException("해당 리프에 대한 권한이 없습니다.");
        }

        Leaf leaf = dto.toLeaf();
        List<Long> leafTagIds = dto.getTagIds();

        Leaf saved = leafEtcService.updateLeafEtc(memberId, leafId, leaf, leafTagIds);

        EtcLeafResponse response = EtcLeafResponse.of(saved, "기타 리프 수정 성공");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/etc/leafs/{leafId}")
    public ResponseEntity<EtcLeafResponse> deleteEtcLeaf(
            @PathVariable Long leafId
    ) {

        Long memberId = 1000L; // TODO: 로그인 정보에서 가져오기

        if (!leafEtcService.isOwner(memberId, leafId)) {
            throw new IllegalArgumentException("해당 리프에 대한 권한이 없습니다.");
        }

        leafEtcService.deleteLeafEtc(leafId);
        EtcLeafResponse response = EtcLeafResponse.of(leafId, "기타 리프 삭제 성공");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}