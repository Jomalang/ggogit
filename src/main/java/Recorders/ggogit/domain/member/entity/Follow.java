package Recorders.ggogit.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Follow {
    private Long memberId;
    private Long followId;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
