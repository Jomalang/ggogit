package io.ggogit.ggogit.domain.member.service;

import io.ggogit.ggogit.domain.member.entity.Member;
import org.springframework.web.multipart.MultipartFile;

public interface MemberImageService {

    public void uploadProfile (Long member, final MultipartFile profile);
    public void uploadBackground (Long member, final MultipartFile background);

}
