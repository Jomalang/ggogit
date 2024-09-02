package Recorders.ggogit.web.memoir;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoirForm {

    @NotEmpty
    private Long treeId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String text;

    @NotEmpty
    private boolean visibility;
}
