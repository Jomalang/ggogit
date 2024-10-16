package io.ggogit.ggogit.api.memoir;

import io.ggogit.ggogit.api.memoir.dto.MemoirRequest;
import io.ggogit.ggogit.api.memoir.dto.MemoirResponse;
import io.ggogit.ggogit.domain.memoir.entity.Memoir;
import io.ggogit.ggogit.domain.memoir.service.MemoirService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("memoir")
@RequiredArgsConstructor
public class MemoirController {

    private final MemoirService memoirService;

    //memoir 등록
    @PostMapping("{id}")
    public ResponseEntity<MemoirResponse> createMemoirResponse(
            @PathVariable(name="id") long treeId,
            @RequestBody MemoirRequest requestDto) {

        if(!requestDto.validate()){
            throw new IllegalArgumentException("올바른 입력이 아닙니다.");
        }

        Memoir memoir = requestDto.toMemoir();
        Long savedId = memoirService.regMemoir(memoir, treeId);
        Memoir result = memoirService.getMemoir(savedId);

        MemoirResponse memoirResponse = MemoirResponse.of(result);
        return new ResponseEntity<>(memoirResponse, HttpStatus.CREATED);

    }

}