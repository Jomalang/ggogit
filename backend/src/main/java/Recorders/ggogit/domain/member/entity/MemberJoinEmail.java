package Recorders.ggogit.domain.member.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinEmail {

    private String to;
    private String subject;
    private String body;
}
