package Recorders.ggogit.web.tree;

import Recorders.ggogit.domain.tree.entity.Seed;
import Recorders.ggogit.domain.tree.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/seed")
public class SeedController {

    @Autowired
    SeedService seedService;

    @GetMapping("/index")
    public String seedIndex( Model model)
    {
        List<Seed> values = seedService.getSeeds();

        model.addAttribute("seeds", values);
        return "view/seed/index";
    }
}
