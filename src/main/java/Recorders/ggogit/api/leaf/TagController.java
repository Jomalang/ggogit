package Recorders.ggogit.api.leaf;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.service.LeafTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("apiTagController")
@RequestMapping("/api/v1/tags")
public class TagController {

    @Autowired
    private LeafTagService leafTagService;

    @GetMapping
    public ResponseEntity<List<LeafTag>> gets(
            @RequestParam(value = "member_id") Long memberId,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") Long page,
            @RequestParam(value = "size", defaultValue = "20") Long size
    ) {
        List<LeafTag> leafTags = leafTagService.getLeafTags(memberId, search, page, size);
        return ResponseEntity.ok(leafTags);
    }

    @PostMapping
    public ResponseEntity<LeafTag> post(
            @RequestBody LeafTag leafTag
    ) {
        System.out.println(leafTag);
        LeafTag saved = leafTagService.register(leafTag);
        return ResponseEntity.ok(saved);
    }
}