package Recorders.ggogit.web.memoir.form;

import Recorders.ggogit.domain.memoir.entity.Memoir;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoirForm {

    private Long treeId;

    @NotBlank(message = "제목을 적어주세요!")
    private String title;

    @NotEmpty(message = "내용을 적어주세요!")
    private String text;

    private boolean visibility;

    private List<String> fileNames = new ArrayList<>();

    public Memoir toMemoir(){
        Memoir memoir = new Memoir();
        memoir.setTreeId(treeId);
        memoir.setTitle(title);
        memoir.setText(text);
        memoir.setVisibility(visibility);

        return memoir;
    }
}
