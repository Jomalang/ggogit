package io.ggogit.ggogit.api.memoir;

import io.ggogit.ggogit.api.memoir.dto.MemoirRequest;
import io.ggogit.ggogit.api.memoir.dto.MemoirDto;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.session.SessionConst;
import io.ggogit.ggogit.domain.memoir.entity.Memoir;
import io.ggogit.ggogit.domain.memoir.service.MemoirService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("memoir")
@RequiredArgsConstructor
public class MemoirController {

    private final MemoirService memoirService;

    //memoir 조회 - 소유권 할당
    @GetMapping("{id}")
    public ResponseEntity<MemoirDto> getMemoir(@PathVariable(name="id") long memoirId,
                                               @SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) Member member) {

        Memoir memoir = memoirService.getMemoir(memoirId);
        MemoirDto memoirResponse = MemoirDto.of(memoir, "");

            //소유권 할당
            if (member != null && memoirService.isOwner(memoirId, member.getId())) {
                memoirResponse.ChangeOwnership(true);
            } else{
                memoirResponse.ChangeOwnership(false);
            }
        return new ResponseEntity<>(memoirResponse, HttpStatus.OK);
    }


    //memoir 등록
    @PostMapping("{id}")
    public ResponseEntity<MemoirDto> createMemoirResponse(
            @PathVariable(name="id") long treeId,
            @RequestBody MemoirRequest requestDto,
            @RequestParam(name="f", required = false) List<String> fileNames) throws IOException {

        if(!requestDto.validate()){
            throw new IllegalArgumentException("올바른 입력이 아닙니다.");
        }

        if(memoirService.isMemoirExist(treeId))
            throw new IllegalStateException("이미 회고록이 있는 트리입니다.");


        Memoir memoir = requestDto.toMemoir();
        Long savedId = memoirService.regMemoir(memoir, treeId);

        try { memoirService.saveImage(fileNames);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("이미지가 존재하지 않습니다.");
        }

        Memoir result = memoirService.getMemoir(savedId);

        MemoirDto memoirResponse = MemoirDto.of(result, "회고록이 등록되었습니다.");
        return new ResponseEntity<>(memoirResponse, HttpStatus.CREATED);
    }

    //수정 - 소유권이 있을때만 요청 가능
    @PutMapping("{id}")
    public ResponseEntity<MemoirDto> updateMemoirResponse(
            @PathVariable(name="id") Long memoirId,
            @RequestBody MemoirRequest requestDto,
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member) throws IllegalAccessException {

        if(!requestDto.validate()){
            throw new IllegalArgumentException("올바른 입력이 아닙니다.");
        }

        if(!memoirService.isMemoirExist(memoirId)){
           throw new IllegalStateException("회고록이 없습니다.");
        }

        if(member == null || !memoirService.isOwner(memoirId, member.getId())){
            throw new IllegalAccessException("회고록 소유자가 아닙니다.");
        }

        Memoir newMemoir = requestDto.toMemoir();
        memoirService.modifyMemoir(newMemoir, memoirId);

        Memoir result = memoirService.getMemoir(memoirId);
        MemoirDto memoirResponse = MemoirDto.of(result, "회고록이 수정되었습니다.");

        return new ResponseEntity<>(memoirResponse, HttpStatus.OK);
    }

    //삭제 - 204 NoContent
    @DeleteMapping("{id}")
    public ResponseEntity<MemoirDto> deleteMemoirResponse(
            @PathVariable(name="id") Long memoirId,
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member) throws IllegalAccessException {

//        if(member == null || !memoirService.isOwner(memoirId, member.getId())){
//            throw new IllegalAccessException("회고록 소유자가 아닙니다.");
//        }
        memoirService.removeMemoir(memoirId);
        return ResponseEntity.noContent().build();
    }
}