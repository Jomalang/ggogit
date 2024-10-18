package io.ggogit.ggogit.api.main;

import io.ggogit.ggogit.api.member.session.SessionConst;
import io.ggogit.ggogit.api.tree.dto.TreeInfoResponse;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.service.MemberService;
import io.ggogit.ggogit.domain.tree.entity.Seed;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.service.SeedService;
import io.ggogit.ggogit.domain.tree.service.TreeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private TreeService treeService;
    @Autowired
    private SeedService seedService;
    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String index(
            @SessionAttribute(value = SessionConst.LOGIN_MEMBER, required = false) Member member
            , Model model
            , RedirectAttributes redirectAttributes) {
        if(member == null){
            return "index";
        }

        model.addAttribute("member", member);
        redirectAttributes.addAttribute("nickName", member.getNickname());
        return "redirect:/home/{nickName}";


    }

    @GetMapping("/home/{nickname}")
    public String index(HttpServletRequest request,
                        Model model
                        ) {

        HttpSession session = request.getSession();

        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if (treeService.getTreeCount(member.getId()) == 0) {
            return "view/home/no-tree";
        } else {
            List<TreeInfoResponse> treeInfoList = new ArrayList<>();
            List<Seed> seedList = seedService.list();
            List<Tree> trees = treeService.findAllByMemberId(member.getId());
            for (Tree tree : trees) {

                LocalDateTime latestLeafDate = tree.getLeaf().;
                Long leafCnt;
                Long likeCnt;
                Long viewCnt;
                TreeInfoResponse treeInfoResponse = TreeInfoResponse.of(tree, latestLeafDate,leafCnt,likeCnt,viewCnt);
                treeInfoList.add(treeInfoResponse);
            }
            Tree tree = memberServic;



            System.out.println(treeInfoList);

            model.addAttribute("treeInfoList", treeInfoList);
            model.addAttribute("seedList", seedList);

            return "view/home/has-tree";
        }
    }
}