package Recorders.ggogit.web.memoir;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoirForm {

    @NotNull
    private Long treeId;

    @NotBlank(message = "제목을 적어주세요!")
    private String title;

    @NotEmpty(message = "내용을 적어주세요!")
    private String text;

    private boolean visibility;
}
