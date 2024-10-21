package Recorders.ggogit.domain.tree.view;

import Recorders.ggogit.domain.leaf.view.LeafBranchView;
import Recorders.ggogit.domain.member.view.MemberImageView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CombineTreeView {

    MemberImageView memberImageView;

    TreeInfoView treeInfoView;

    List<LeafBranchView> leafList;


}
