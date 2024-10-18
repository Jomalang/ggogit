package io.ggogit.ggogit.api.leaf;

import io.ggogit.ggogit.api.leaf.dto.LeafBeforeInfoResponse;
import io.ggogit.ggogit.api.leaf.dto.LeafDetailResponse;
import io.ggogit.ggogit.domain.leaf.service.LeafDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LeafController {

    private final LeafDtoService leafDtoService;

    // 리프 디테일 정보
    @GetMapping("/leaves/{leafId}/before-info")
    public ResponseEntity<LeafBeforeInfoResponse> getLeafBeforeInfo(
            @PathVariable Long leafId
    ) {
        LeafBeforeInfoResponse response = leafDtoService.findLeafBeforeInfo(leafId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 리프 책 디테일 정보
    @GetMapping("/leaves/{leafId}")
    public ResponseEntity<LeafDetailResponse> getLeafBookDetail(
            @PathVariable Long leafId
    ) {
        LeafDetailResponse response = leafDtoService.findLeafBookDetail(leafId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}