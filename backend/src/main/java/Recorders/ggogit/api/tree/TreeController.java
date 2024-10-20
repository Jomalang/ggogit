package Recorders.ggogit.api.tree;

import Recorders.ggogit.domain.member.entity.Member;
import Recorders.ggogit.domain.tree.service.TreeService;
import Recorders.ggogit.domain.tree.view.TreeCardView;
import Recorders.ggogit.web.member.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("apiTreeController")
@RequestMapping("/api/tree/")
@RequiredArgsConstructor
public class TreeController {

    private final TreeService treeService;

    @GetMapping("/list")
    private List<TreeCardView> list(
            @RequestParam(value = "seed", required = false) Long seedId,
            HttpServletRequest request
    ) {

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Long memberId = member.getId();

        List<TreeCardView> list = treeService.findTreeCardView(seedId,memberId);
        return list;
    }
}
