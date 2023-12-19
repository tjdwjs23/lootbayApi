package kr.ne.abc.lootbayapi.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
class WebController {

    /**
     * index()
     *
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
}