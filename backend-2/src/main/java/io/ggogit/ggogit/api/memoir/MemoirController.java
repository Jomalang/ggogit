package io.ggogit.ggogit.api.memoir;

import io.ggogit.ggogit.api.memoir.dto.MemoirRequest;
import io.ggogit.ggogit.api.memoir.dto.MemoirResponse;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.memoir.entity.Memoir;
import io.ggogit.ggogit.domain.memoir.service.MemoirService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("memoir")
@RequiredArgsConstructor
public class MemoirController {

    private final MemoirService memoirService;

    //memoir 조회
    @GetMapping("{id}")
    public ResponseEntity<MemoirResponse> getMemoir(@PathVariable long memoirId) {
        Memoir memoir = memoirService.getMemoir(memoirId);
    }


    //memoir 등록
    @PostMapping("{id}")
    public ResponseEntity<MemoirResponse> createMemoirResponse(
            @PathVariable(name="id") long treeId,
            @RequestBody MemoirRequest requestDto,
            @RequestParam(name="f", required = false) List<String> fileNames) throws IOException {

        if(!requestDto.validate()){
            throw new IllegalArgumentException("올바른 입력이 아닙니다.");
        }

        if(memoirService.isMemoirExist(treeId)){
            throw new IllegalStateException("이미 회고록이 있는 트리입니다.");
        }

        Memoir memoir = requestDto.toMemoir();
        Long savedId = memoirService.regMemoir(memoir, treeId);

        try { memoirService.saveImage(fileNames);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Memoir result = memoirService.getMemoir(savedId);

        MemoirResponse memoirResponse = MemoirResponse.of(result);
        return new ResponseEntity<>(memoirResponse, HttpStatus.CREATED);
    }

    //수정
    @PutMapping("{id}")
    public ResponseEntity<MemoirResponse> updateMemoirResponse(
            @PathVariable(name="id") Long memoirId,
            @RequestBody MemoirRequest requestDto,
            @SessionAttribute Member member) throws IllegalAccessException {

        if(!requestDto.validate()){
            throw new IllegalArgumentException("올바른 입력이 아닙니다.");
        }

        if(!memoirService.isMemoirExist(memoirId)){
           throw new IllegalStateException("회고록이 없습니다.");
        }

        if(!memoirService.isOwner(memoirId, memoirId)){
            throw new IllegalAccessException("회고록 소유자가 아닙니다.");
        }

        Memoir newMemoir = requestDto.toMemoir();
        memoirService.modifyMemoir(newMemoir, memoirId);

        Memoir result = memoirService.getMemoir(memoirId);
        MemoirResponse memoirResponse = MemoirResponse.of(result);

        return new ResponseEntity<>(memoirResponse, HttpStatus.OK);
    }
}