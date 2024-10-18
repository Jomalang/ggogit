package io.ggogit.ggogit.api.member.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RequiredArgsConstructor
public class MemberResponse {
    private Long id;
    private String username;
}
