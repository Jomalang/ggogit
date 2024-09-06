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
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String nickname;
    @NotNull
    private String username;
    private String introduction;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
