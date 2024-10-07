package Recorders.ggogit.api.leaf.dto;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafTagDto {

    private Long id;
    private String name;

    public static LeafTagDto of(LeafTag entity) {
        return LeafTagDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}