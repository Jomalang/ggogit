package Recorders.ggogit.web.leaf;

import Recorders.ggogit.domain.leaf.view.*;
import Recorders.ggogit.domain.member.entity.Member;
import Recorders.ggogit.domain.tree.entity.Seed;
import Recorders.ggogit.domain.tree.service.SeedService;
import Recorders.ggogit.domain.leaf.service.LeafBookService;
import Recorders.ggogit.domain.leaf.service.LeafEtcService;
import Recorders.ggogit.domain.leaf.service.LeafService;
import Recorders.ggogit.domain.tree.service.TreeService;
import Recorders.ggogit.web.leaf.form.LeafBookForm;
import Recorders.ggogit.web.leaf.form.LeafForm;
import Recorders.ggogit.web.member.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller("WebLeafController")
@RequestMapping("/leaf")
public class LeafController {

    @Autowired
    private LeafEtcService leafEtcService;

    @Autowired
    private LeafBookService leafBookService;

    @Autowired
    private LeafService leafService;

    @Autowired
    private SeedService seedService;

    @Autowired
    private TreeService treeService;

    @GetMapping("/first/reg")
    public ModelAndView firstReg(
            @RequestParam(value = "seed", required = false) String type,
            HttpServletRequest request
    ) {
        Member member = (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        Long memberId = member.getId();

        ModelAndView mv;
        if (seedService.isBook(type)) {
            mv = new ModelAndView("view/leaf/1st-reg-book");
            mv.addObject("form", new LeafBookForm());
        } else {
            Seed seed = seedService.getByEngName(type);
            System.out.println(seed.toString());
            mv = new ModelAndView("view/leaf/1st-reg-etc");
            mv.addObject("seedId", seed.getId());
            mv.addObject("form", new LeafForm());
        }
        mv.addObject("memberId", memberId);
        return mv;
    }

    @PostMapping("/first/reg")
    public ModelAndView firstReg(
            @Valid @ModelAttribute("form") LeafBookForm form,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {

        Member member = (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        Long memberId = member.getId();
        Long seedId = form.getSeedId();

        if (seedService.isBookById(seedId)) { // 도서 리프 에러 처리
            if (bindingResult.hasErrors()) {
                return new ModelAndView("view/leaf/1st-reg-book", "form", form);
            }
            LeafBookView leafBookView = leafBookService.register(form.toLeafBookView(), seedId, memberId); // 도서 리프 등록

            String url = UriComponentsBuilder.fromPath("/leaf/list")
                    .queryParam("tree_id", leafBookView.getTreeId())
                    .queryParam("leaf_id", leafBookView.getLeafId())
                    .toUriString();

            return new ModelAndView("redirect:" + url);
        }

        if (bindingResult.hasErrors()) { // ETC 리프 에러 처리
            return new ModelAndView("view/leaf/1st-reg-etc", "form", form);
        }

        LeafEtcView leafEtcView = leafEtcService.register(form.toLeafEtcView(), memberId); // ETC 리프 등록

        String url = UriComponentsBuilder.fromPath("/leaf/list")
                .queryParam("tree_id", leafEtcView.getTreeId())
                .queryParam("leaf_id", leafEtcView.getLeafId())
                .toUriString();

        return new ModelAndView("redirect:" + url);
    }

    @GetMapping("/reg")
    public ModelAndView reg(
            @RequestParam(value = "tree_id") Long treeId,
            @RequestParam(value = "leaf_id") Long leafId,
            HttpServletRequest request
    ) {
        Member member = (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        Long memberId = member.getId();

        Long seedId = treeService.getSeedId(treeId);
        BeforeLeafInfoView beforeLeafInfoView = leafService.getBeforeLeafInfoView(leafId);

        if (seedService.isBook(seedId)) {
            ModelAndView mv = new ModelAndView("view/leaf/reg-book", "form", new LeafBookForm());
            mv.addObject("beforeLeaf", beforeLeafInfoView);
            mv.addObject("memberId", memberId);
            mv.addObject("treeId", treeId);
            mv.addObject("leafId", leafId);
            mv.addObject("seedId", seedId);
            return mv;
        }

        ModelAndView mv = new ModelAndView("view/leaf/reg-etc", "form", new LeafForm());
        mv.addObject("beforeLeaf", beforeLeafInfoView);
        mv.addObject("memberId", memberId);
        mv.addObject("treeId", treeId);
        mv.addObject("leafId", leafId);
        mv.addObject("seedId", seedId);
        return mv;
    }

    @PostMapping("/reg")
    public ModelAndView reg(
            @Validated @ModelAttribute("form") LeafBookForm form,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        Member member = (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        Long memberId = member.getId();
        Long seedId = form.getSeedId();

        if (seedService.isBookById(seedId)) {
            if (bindingResult.hasErrors()) {
                return new ModelAndView("view/leaf/reg-book", "form", form);
            }

            LeafBookView leafBookView = leafBookService.register(form.toLeafBookView(), seedId, memberId); // 도서 리프 등록

            String url = UriComponentsBuilder.fromPath("/leaf/list")
                    .queryParam("tree_id", leafBookView.getTreeId())
                    .queryParam("leaf_id", leafBookView.getLeafId())
                    .toUriString();

            return new ModelAndView("redirect:" + url);
        }

        if (bindingResult.hasErrors()) { // ETC 리프 에러 처리
            return new ModelAndView("view/leaf/reg-etc", "form", form);
        }

        LeafEtcView leafEtcView = leafEtcService.register(form.toLeafEtcView(), memberId); // ETC 리프 등록

        String url = UriComponentsBuilder.fromPath("/leaf/list")
                .queryParam("tree_id", leafEtcView.getTreeId())
                .queryParam("leaf_id", leafEtcView.getLeafId())
                .toUriString();

        return new ModelAndView("redirect:" + url);
    }

    @GetMapping("/edit")
    public ModelAndView edit(
            @RequestParam(value = "seed", required = false) Long seed,
            @RequestParam(value = "id", required = false) Long id,
            Model model
    ) {
        if (seedService.isBookById(seed)) {
            return new ModelAndView("view/leaf/edit-book","form", leafBookService.getLeafBookView(id));
        } else {
            return new ModelAndView("view/leaf/edit-etc","form", leafEtcService.getLeafEtcView(id));
        }
    }

    @GetMapping("/list")
    public String list(
        @RequestParam(value = "tree_id") Long treeId,
        @RequestParam(value = "leaf_id") Long leafId,
        Model model
    ) {
        Long memberId = 999L;
        boolean isOwner = leafService.isOwner(treeId, memberId);
        List<LeafItemView> list = leafService.getLeafItems(treeId, leafId, isOwner);

        // 최근 수정 브랜치 이름 정보 넣어야함
        model.addAttribute("focusedTime", list.getLast().getCreateTime());
        model.addAttribute("branch", leafService.getBranchInfo(treeId, list.getLast().getId()));
        model.addAttribute("breadcrumb", leafService.getBreadcrumb(treeId, leafId));
        return "view/leaf/list";
    }

    @GetMapping("/detail")
    public String getLeafDetail(
            @RequestParam(value = "leaf_id") Long leafId,
            Model model
    ) {
        model.addAttribute("breadcrumb", leafService.getBreadcrumb(leafId));
        // TODO: 도서 상세 정보 넣어야함

        Long memberId = 1L;
        List<LeafImageCardView> leafImageCardViews = leafService.getLeafImageCardViews(memberId);
        model.addAttribute("leafImageCardViews", leafImageCardViews);
        return "view/leaf/list";
    }

    @GetMapping("/list/test")
    public String listTest(
    ) {
        return "view/leaf/list-test";
    }
}