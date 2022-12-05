package HwangJiHun.poeitemvalues.controller;

import HwangJiHun.poeitemvalues.domain.feign.MemberClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberClient memberClient;

    public MemberController(MemberClient memberClient) {
        this.memberClient = memberClient;
    }

    @GetMapping("/add")
    public String memberAdd(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("preUri", "http://localhost:8080/");
        return "redirect:http://localhost:8091/members/add";
    }
}
