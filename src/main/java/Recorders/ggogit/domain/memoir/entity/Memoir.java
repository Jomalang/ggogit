package Recorders.ggogit.domain.memoir.entity;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Memoir {

    private long id;
    @NotNull
    private long treeId;
    @NotNull
    private String title;
    private String text;
    private Boolean visibility;
    private LocalDate updateTime;
    private LocalDate createTime;

}
