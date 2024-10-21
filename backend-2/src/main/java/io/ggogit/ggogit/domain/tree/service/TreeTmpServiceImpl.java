package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.api.tree.dto.TreeTmpRequest;
import io.ggogit.ggogit.domain.book.entity.BookCategory;
import io.ggogit.ggogit.domain.book.repository.BookCategoryRepository;
import io.ggogit.ggogit.domain.book.repository.BookRepository;
import io.ggogit.ggogit.domain.image.repository.ImageRepository;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.tree.entity.Seed;
import io.ggogit.ggogit.domain.tree.entity.TreeTmp;
import io.ggogit.ggogit.domain.tree.repository.SeedRepository;
import io.ggogit.ggogit.domain.tree.repository.TreeTmpRepository;
import io.ggogit.ggogit.type.UploadFolderType;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TreeTmpServiceImpl implements TreeTmpService {

    private final TreeTmpRepository treeTmpRepository;
    private final BookCategoryRepository bookCategoryRepository;
    private final SeedRepository seedRepository;
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public Long tmpTreeSave(TreeTmp treeTmp, Member member, Long seedId, @Nullable Long bookCategoryId, @Nullable byte[] img, @Nullable String imgFileName) {


        if (bookCategoryId != null) {
            BookCategory bookCategory = bookCategoryRepository.findById(bookCategoryId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
            treeTmp.setBookCategory(bookCategory);
        }

        Seed seed = seedRepository.findById(seedId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 씨앗입니다."));
        treeTmp.setSeed(seed);

        if (imgFileName != null) {
            String fileName = imageRepository.changeFileNameToUUID(imgFileName);
            treeTmp.setImageFile(fileName);
        }

        treeTmpRepository.save(treeTmp);


        if (img != null) {
            imageRepository.saveImage(treeTmp.getImageFile(), img, UploadFolderType.TMP);
        }
        return treeTmp.getId();
    }

    @Override
    public void deleteTmpById(Long memberId) {
        if (treeTmpRepository.findByMemberId(memberId).isPresent())
            treeTmpRepository.deleteById(memberId);
    }
}
