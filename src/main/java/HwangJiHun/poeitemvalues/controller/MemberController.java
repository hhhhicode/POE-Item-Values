package HwangJiHun.poeitemvalues.controller;

import HwangJiHun.poeitemvalues.domain.feign.MemberClient;
import HwangJiHun.poeitemvalues.domain.members.MemberConst;
import HwangJiHun.poeitemvalues.model.members.Member;
import HwangJiHun.poeitemvalues.model.members.MemberSessionDto;
import HwangJiHun.poeitemvalues.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberClient memberClient;
    private final MemberService memberService;

    public MemberController(MemberClient memberClient, MemberService memberService) {
        this.memberClient = memberClient;
        this.memberService = memberService;
    }

    @GetMapping("/add")
    public String memberAdd(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(MemberConst.HOME_URI, "http://localhost:8080");
        redirectAttributes.addAttribute(MemberConst.LOGIN_URI, "http://localhost:8080/members/login");
        return "redirect:http://localhost:8091/members/add";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request,
                        @RequestParam(required = false) String homeUri,
                        @RequestParam(required = false) String userId) {

        Optional<Member> loginMember = memberService.findByUserId(userId);
        if (loginMember.isPresent()) {
            Member member = loginMember.get();
            Long id = member.getId();
            MemberSessionDto memberSessionDto = new MemberSessionDto();
            memberSessionDto.setUserId(member.getUserId());
            memberSessionDto.setUserName(member.getUserName());
            memberSessionDto.setIcon(member.getIcon());
            memberSessionDto.setEmailAddress(member.getEmailAddress());
            memberSessionDto.setDisplayPrograms(member.getDisplayPrograms());

            HttpSession session = request.getSession(true);
            session.setAttribute(MemberConst.MEMBER_SESSION_DTO, memberSessionDto);
        }

        return "redirect:" + homeUri;
    }
}
