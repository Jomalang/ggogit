package Recorders.ggogit.domain.memoir;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Memoir {

    private long id;
    @NotNull
    private long treeId;
    @NotNull
    private String title;
    private String text;
    private LocalDate updateDate;
    private LocalDate createDate;

}
