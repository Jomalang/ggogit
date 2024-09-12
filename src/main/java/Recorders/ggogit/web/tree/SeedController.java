package Recorders.ggogit.web.tree;

import Recorders.ggogit.domain.tree.view.SeedView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/seed")
public class SeedController {

    @GetMapping("/index")
    public String seedIndex( Model model)
    {
        List<SeedView> values = new ArrayList<>();

        SeedView seed;
        seed = SeedView.builder()
                .name("도서")
                .category("book")
                .description("book")
                .build();
        values.add(seed);
        seed = SeedView.builder()
                .name("생각")
                .category("etc")
                .description("idea")
                .build();
        values.add(seed);
        seed = SeedView.builder()
                .name("문장")
                .category("etc")
                .description("phrase")
                .build();
        values.add(seed);
        seed = SeedView.builder()
                .name("공부")
                .category("etc")
                .description("study")
                .build();
        values.add(seed);
        seed = SeedView.builder()
                .name("영상")
                .category("etc")
                .description("video")
                .build();
        values.add(seed);

        model.addAttribute("seeds", values);
        return "view/seed/index";
    }
}
