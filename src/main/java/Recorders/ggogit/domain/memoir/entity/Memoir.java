package Recorders.ggogit.domain.memoir.entity;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Memoir {

    private Long id;
    private Long treeId;
    private String title;
    private String text;
    private Boolean visibility;
    private LocalDate updateTime;
    private LocalDate createTime;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeText(String text) {
        this.text = text;
    }

    public void changeVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

}
