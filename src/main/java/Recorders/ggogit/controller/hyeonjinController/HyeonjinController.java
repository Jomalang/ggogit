package Recorders.ggogit.controller.hyeonjinController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/hyeonjin")
public class HyeonjinController {

    @GetMapping("/page0")
    public String getMethodName() {
        return "view/tree/register/0-index";
    }

}
