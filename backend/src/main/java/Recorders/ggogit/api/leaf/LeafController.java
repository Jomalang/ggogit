package Recorders.ggogit.api.leaf;

import Recorders.ggogit.api.leaf.dto.LeafItemDto;
import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.service.LeafService;
import Recorders.ggogit.domain.leaf.service.LeafTagService;
import Recorders.ggogit.domain.leaf.structure.LeafNode;
import Recorders.ggogit.domain.leaf.view.LeafBranchView;
import Recorders.ggogit.domain.leaf.view.LeafListBranchView;
import Recorders.ggogit.domain.member.entity.Member;
import Recorders.ggogit.domain.member.service.MemberService;
import Recorders.ggogit.domain.tree.service.TreeService;
import Recorders.ggogit.web.member.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController("apiLeafController")
@RequestMapping("/api/v1/")
@Slf4j
@RequiredArgsConstructor
public class LeafController {

    private final LeafService leafService;
    private final LeafTagService leafTagService;
    private final TreeService treeService;

    @Value("${file.tmp-dir}")
    private String tmpDir;

    @GetMapping("/trees/{treeId}/leafs/{leafId}/children")
    public ResponseEntity<List<LeafItemDto>> getLeafNodesToEnd(
            @PathVariable Long treeId,
            @PathVariable Long leafId
    ) {
        Long memberId = 1L;
        boolean isOwner = leafService.isOwner(treeId, memberId); // 자신의 권한 확인
        List<LeafNode> leafNodes = leafService.getLeafNodeFromLeafIdToEnd(treeId, leafId, isOwner);
        List<LeafItemDto> leafItemDtos = convertLeafNodesToLeafItemDtos(leafNodes);
        return ResponseEntity.ok(leafItemDtos);
    }

    @GetMapping("/trees/{treeId}/leafs/{leafId}")
    public ResponseEntity<List<LeafItemDto>> getLeafNodes(
            @PathVariable Long treeId,
            @PathVariable Long leafId
    ) {
        Long memberId = 1L;
        boolean isOwner = leafService.isOwner(treeId, memberId); // 자신의 권한 확인
        List<LeafNode> leafNodes = leafService.getLeafNodeAll(treeId, leafId, isOwner);
        List<LeafItemDto> leafItemDtos = convertLeafNodesToLeafItemDtos(leafNodes);
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

    @PostMapping("/leaf/image-upload")
    public String uploadEditorImage(
            @RequestParam final MultipartFile image
    ) {
        if (image.isEmpty()) {
            throw new RuntimeException("이미지가 없습니다.");
        }

        String filename = image.getOriginalFilename(); //원본 이미지명
        String saveFilename = changeFileNameToUUID(filename);
        Path fileFullPath = Paths.get(tmpDir, saveFilename); //  경로 획득

        if (!Files.exists(fileFullPath.getParent())) { // 폴더 경로 존재 확인
            try {
                Files.createDirectories(fileFullPath.getParent());
            } catch (IOException e) {
                throw new RuntimeException("폴더 생성 실패", e);
            }
        }

        try {
            image.transferTo(fileFullPath);
            log.info("saveFilename = {}", saveFilename);
            return saveFilename; //업로드된 파일명 반환

        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패", e);
        }
    }

    @GetMapping(
            value = "/leaf/image-print",
            produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE}
    )
    public byte[] printEditorImage(
            @RequestParam final String filename
    ) {

        Path fileFullPath = Paths.get(tmpDir, filename); //업로드된 파일의 전체 경로 획득

        if (!fileFullPath.toFile().exists()) { //파일이 없는 경우 예외 처리
            throw new RuntimeException("파일이 존재하지 않습니다.");
        }

        try {
            return Files.readAllBytes(fileFullPath); //이미지 파일을 byte[]로 변환 후 반환
        } catch (IOException e) {
            throw new RuntimeException("파일 읽기 실패", e);
        }
    }

    private List<LeafItemDto> convertLeafNodesToLeafItemDtos(List<LeafNode> leafNodes) {
        List<LeafItemDto> leafItemDtos = new ArrayList<>();
        for (LeafNode leafNode : leafNodes) {
            if (leafNode.getData().getVisibility()) {
                List<LeafTag> leafTags = leafTagService.getLeafTagsByLeafId(leafNode.getData().getId());
                leafItemDtos.add(LeafItemDto.of(leafNode, leafTags));
            } else {
                leafItemDtos.add(LeafItemDto.of(leafNode, List.of()));
            }
        }
        return leafItemDtos;
    }

    private String changeFileNameToUUID(String fileName) {
        String extension = getFileExtension(fileName);
        return generateUUIDWithoutHyphens() + "." + extension;
    }

    private static String getFileExtension(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("fileName is null");
        }

        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // 확장자가 없는 경우
        }

        return fileName.substring(lastIndexOf + 1);
    }

    private static String generateUUIDWithoutHyphens() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    @GetMapping("/filter")
    private List<LeafBranchView>  leafFiltering(
            @RequestParam(value = "treeId") final Long treeId,
            @RequestParam(value = "bookMark", required = false) final Boolean bookMark,
            @RequestParam(value = "filter", required = false) final  Long filter,
            @RequestParam(value = "sort", required = false) final  Long sort,
            HttpServletRequest request
    ){
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Long memberId = member.getId();
//        Long memberIdByTreeId = treeService.

        int page = 10;
        List<LeafBranchView> list = leafService.findBranch(treeId, bookMark, filter, sort, page);

        return list;

    }

}