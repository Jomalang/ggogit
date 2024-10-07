package Recorders.ggogit.domain.member.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String username;
    private String introduction;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
