package Recorders.ggogit.domain.memoir.entity;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Memoir {

    private Long id;
    @NotNull
    private Long treeId;
    @NotNull
    private String title;
    private String text;
    private Boolean visibility;
    private LocalDate updateTime;
    private LocalDate createTime;

}
