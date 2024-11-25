package io.ggogit.ggogit.domain.member.repository.query;

import io.ggogit.ggogit.api.member.dto.MemberDomainCntResponse;

public interface MemberQueryRepository {

    Long findBookCntById(Long memberId);
}
