package io.ggogit.ggogit.domain.member.service;

import io.ggogit.ggogit.domain.image.repository.ImageRepositoryImpl;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.entity.MemberBackgroundImage;
import io.ggogit.ggogit.domain.member.entity.MemberProfileImage;
import io.ggogit.ggogit.domain.member.repository.MemberBackgroundImageRepository;
import io.ggogit.ggogit.domain.member.repository.MemberProfileImageRepository;
import io.ggogit.ggogit.type.UploadFolderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberImageServiceImpl implements MemberImageService {

    private final ImageRepositoryImpl imageRepository;
    private final MemberProfileImageRepository memberProfileImageRepository;
    private final MemberBackgroundImageRepository memberBackgroundImageRepository;
    private final MemberService memberService;

    //프로필 이미지 업로드
    public void uploadProfile (Long memberId, final MultipartFile profile) {
        if(profile.isEmpty()) return;

        Member member = memberService.findById(memberId);
        //이미지 파일명 생성
        String saveName = imageRepository.changeFileNameToUUID(profile.getOriginalFilename());
        try{

            //기존 이미지가 있다면
            if(memberProfileImageRepository.existsById(memberId)) {
                MemberProfileImage memberProfileImage = memberProfileImageRepository.findById(memberId).get();
                //실제 데이터 삭제
                imageRepository.deleteImage(memberProfileImage.getName(), UploadFolderType.MEMBER);
                //DB 데이터 교체
                memberProfileImageRepository.findById(memberId).ifPresent(memberProfileImage1 -> {
                    memberProfileImage1.setName(saveName);
                    memberProfileImageRepository.save(memberProfileImage1);
                });
            } else{
                //기존 이미지가 없다면 바로 저장
                memberProfileImageRepository.save(MemberProfileImage.of(member, saveName));
            }
            //member폴더에 실제 이미지 저장
            imageRepository.saveImage(saveName, profile.getBytes(), UploadFolderType.MEMBER);
        } catch (IOException e) {
            //TODO 예외처리
            throw new RuntimeException(e);
        }
    }

    //배경 이미지 업로드
    public void uploadBackground (Long memberId, final MultipartFile background) {
        if(background.isEmpty()) return;

        Member member = memberService.findById(memberId);

        //이미지 파일명 생성
        String saveName = imageRepository.changeFileNameToUUID(background.getOriginalFilename());
        try{
            //기존 이미지 삭제
            if(memberBackgroundImageRepository.existsById(memberId)) {
                MemberBackgroundImage memberBackgroundImage = memberBackgroundImageRepository.findById(memberId).get();
                //실제 데이터 삭제
                imageRepository.deleteImage(memberBackgroundImage.getName(), UploadFolderType.MEMBER_BACKGROUND);
                //DB 데이터 교체
                memberBackgroundImageRepository.findById(memberId).ifPresent(memberBackgroundImage1 -> {
                    memberBackgroundImage1.setName(saveName);
                    memberBackgroundImageRepository.save(memberBackgroundImage1);
                });
            } else{
                //기존 이미지가 없다면 바로 저장
                memberBackgroundImageRepository.save(MemberBackgroundImage.of(member, saveName));
            }
            //memberBackground 폴더에 실제이미지 저장
            imageRepository.saveImage(saveName, background.getBytes(), UploadFolderType.MEMBER_BACKGROUND);
        } catch (IOException e) {
            //TODO 예외처리
            throw new RuntimeException(e);
        }
    }

}
