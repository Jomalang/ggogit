package io.ggogit.ggogit.api.leaf;


import io.ggogit.ggogit.api.leaf.dto.EtcLeafEditResponse;
import io.ggogit.ggogit.api.leaf.dto.EtcLeafRequest;
import io.ggogit.ggogit.api.leaf.dto.EtcLeafResponse;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.service.LeafEtcService;
import io.ggogit.ggogit.domain.member.security.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LeafEtcController {

    private final LeafEtcService leafEtcService;

    @PostMapping("/etc/first/leaves")
    public ResponseEntity<EtcLeafResponse> createFirstEtcLeaf(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody EtcLeafRequest dto
    ) {
        dto.isValidate();
        Long memberId = userDetails.getId();
        Leaf leaf = dto.toLeaf();
        List<Long> leafTagIds = dto.getTagIds();

        Leaf saved = leafEtcService.createFirstLeafEtc(memberId, leaf, leafTagIds);

        EtcLeafResponse response = EtcLeafResponse.of(saved, "첫번째 기타 리프 생성 성공");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/etc/leaves/{parentLeafId}")
    public ResponseEntity<EtcLeafResponse> createEtcLeaf(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long parentLeafId,
            @Valid @RequestBody EtcLeafRequest dto
    ) {
        dto.isValidate();
        Long memberId = userDetails.getId();

        if (!leafEtcService.isOwner(memberId, parentLeafId)) {
            throw new IllegalArgumentException("해당 리프에 대한 권한이 없습니다.");
        }

        Leaf leaf = dto.toLeaf();
        List<Long> leafTagIds = dto.getTagIds();

        Leaf saved = leafEtcService.createLeafEtc(memberId, parentLeafId, leaf, leafTagIds);

        EtcLeafResponse response = EtcLeafResponse.of(saved, "기타 리프 생성 성공");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/etc/leaves/{leafId}")
    public ResponseEntity<EtcLeafResponse> updateEtcLeaf(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long leafId,
            @Valid @RequestBody EtcLeafRequest dto
    ) {
        dto.isValidate();
        Long memberId = userDetails.getId();

        if (!leafEtcService.isOwner(memberId, leafId)) {
            throw new IllegalArgumentException("해당 리프에 대한 권한이 없습니다.");
        }

        Leaf leaf = dto.toLeaf();
        List<Long> leafTagIds = dto.getTagIds();

        Leaf saved = leafEtcService.updateLeafEtc(memberId, leafId, leaf, leafTagIds);

        EtcLeafResponse response = EtcLeafResponse.of(saved, "기타 리프 수정 성공");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/etc/leaves/{leafId}")
    public ResponseEntity<EtcLeafResponse> deleteEtcLeaf(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long leafId
    ) {
        Long memberId = userDetails.getId();

        if (!leafEtcService.isOwner(memberId, leafId)) {
            throw new IllegalArgumentException("해당 리프에 대한 권한이 없습니다.");
        }

        leafEtcService.deleteLeafEtc(leafId);
        EtcLeafResponse response = EtcLeafResponse.of(leafId, "기타 리프 삭제 성공");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/etc/leaves/{leafId}/edit")
    public ResponseEntity<EtcLeafEditResponse> getEtcLeafEdit(
            @PathVariable Long leafId
    ) {
        EtcLeafEditResponse response = leafEtcService.getLeafEtcEdit(leafId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}