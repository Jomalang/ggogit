package Recorders.ggogit.web.leaf;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/leaf")
public class LeafController {

    @GetMapping("/reg")
    public String getLeafReg(
            @RequestParam(value = "first", required = false) boolean first,
            @RequestParam(value = "seed", required = false) Integer seed,
            Model model
    ) {
        if (first) {
            if (seed == 1) {
                return "/view/leaf/1st-reg-book";
            } else {
                return "/view/leaf/1st-reg-etc";
            }
        } else {
            if (seed == 1) {
                return "/view/leaf/reg-book";
            } else {
                return "/view/leaf/reg-etc";
            }
        }
    }

    @PostMapping("/reg")
    public String PostLeafReg() {
        return "redirect:/leaf/reg";
    }

    @GetMapping("/edit")
    public String getLeafEdit(
            @RequestParam(value = "seed", required = false) Integer seed,
            @RequestParam(value = "id", required = false) Integer id,
            Model model
    ) {
        if (seed == 1) {
            return "/view/leaf/edit-book";
        } else {
            return "/view/leaf/edit-etc";
        }
    }

    @GetMapping("/list")
    public String getLeafList() {
        return "/view/leaf/list";
    }

    @GetMapping("/detail")
    public String getLeafDetail() {
        return "/view/leaf/list";
    }

}
