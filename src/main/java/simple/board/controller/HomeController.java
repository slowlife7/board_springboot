package simple.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import simple.board.SessionConst;
import simple.board.model.User;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    String home() {
        return "forward:/post";
    }
}
