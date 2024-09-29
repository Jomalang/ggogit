package Recorders.ggogit.api.leaf;

import Recorders.ggogit.api.leaf.dto.LeafItemDto;
import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.service.LeafService;
import Recorders.ggogit.domain.leaf.service.LeafTagService;
import Recorders.ggogit.domain.leaf.structure.LeafNode;
import Recorders.ggogit.domain.leaf.view.LeafListBranchView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("apiLeafController")
@RequestMapping("/api/v1/")
public class LeafController {

    @Autowired
    private LeafService leafService;

    @Autowired
    private LeafTagService leafTagService;

    @GetMapping("/trees/{treeId}/leafs/{leafId}/children")
    public ResponseEntity<List<LeafItemDto>> getLeafNodesToEnd(
            @PathVariable Long treeId,
            @PathVariable Long leafId
    ) {
        List<LeafNode> leafNodes = leafService.getLeafNodeFromLeafIdToEnd(treeId, leafId);

        List<LeafItemDto> leafItemDtos = new ArrayList<>();
        for (LeafNode leafNode : leafNodes) {
            List<LeafTag> leafTags = leafTagService.getLeafTagsByLeafId(leafNode.getData().getId());
            leafItemDtos.add(LeafItemDto.of(leafNode, leafTags));
        }

        return ResponseEntity.ok(leafItemDtos);
    }

    @GetMapping("/trees/{treeId}/leafs/{leafId}")
    public ResponseEntity<List<LeafItemDto>> getLeafNodes(
            @PathVariable Long treeId,
            @PathVariable Long leafId
    ) {
        List<LeafNode> leafNodes = leafService.getLeafNodeAll(treeId, leafId);

        List<LeafItemDto> leafItemDtos = new ArrayList<>();
        for (LeafNode leafNode : leafNodes) {
            List<LeafTag> leafTags = leafTagService.getLeafTagsByLeafId(leafNode.getData().getId());
            leafItemDtos.add(LeafItemDto.of(leafNode, leafTags));
        }

        return ResponseEntity.ok(leafItemDtos);
    }

    @GetMapping("/trees/{treeId}/leafs/{leafId}/branch")
    public ResponseEntity<LeafListBranchView> getLeafBranch(
            @PathVariable Long treeId,
            @PathVariable Long leafId
    ) {
        LeafListBranchView leafLIstBranchView = leafService.getBranchInfo(treeId, leafId);
        return ResponseEntity.ok(leafLIstBranchView);
    }
}