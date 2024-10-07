package Recorders.ggogit.domain.member.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberImageView {

    private Long id;
    private String nickName;
    private String userName;
    private String profileImage;
}
