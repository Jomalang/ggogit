package io.ggogit.ggogit.api.leaf.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeafTagRequest {

    @NotBlank(message = "태그 이름을 입력해주세요.")
    private String name;
}