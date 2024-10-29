package io.ggogit.ggogit.api.tree.dto;

import io.ggogit.ggogit.domain.tree.entity.Tree;
import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreeLightInfoResponse {

   private Long id;
   private Long memberId;
   private Long seedId;
   private Long bookId;
   private String title;
   private String description;
   private Boolean visibility;
   private String createdAt;
   private String updatedAt;

   public static TreeLightInfoResponse of(Tree Tree){
         return TreeLightInfoResponse.builder()
                .id(Tree.getId())
                .memberId(Tree.getMember().getId())
                .seedId(Tree.getSeed().getId())
                .bookId(Tree.getBook().getId())
                .title(Tree.getTitle())
                .description(Tree.getDescription())
                .visibility(Tree.getVisibility())
                .createdAt(Tree.getCreateTime().toString())
                .updatedAt(Tree.getUpdateTime().toString())
                .build();
   }
}
