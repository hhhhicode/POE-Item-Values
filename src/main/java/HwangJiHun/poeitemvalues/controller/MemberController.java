package HwangJiHun.poeitemvalues.controller;

import HwangJiHun.poeitemvalues.domain.feign.MemberClient;
import HwangJiHun.poeitemvalues.domain.members.MemberConst;
import HwangJiHun.poeitemvalues.model.members.Member;
import HwangJiHun.poeitemvalues.model.members.MemberSessionDto;
import HwangJiHun.poeitemvalues.service.MemberService;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${homeUri}")
    private String homeUri;
    @Value("${memberUri}")
    private String memberUri;

    @GetMapping("/add")
    public String memberAdd(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(MemberConst.HOME_URI, homeUri);
        redirectAttributes.addAttribute(MemberConst.LOGIN_URI, homeUri + "/members/login/logic");
        return "redirect:" + memberUri + "/members/add";
    }

    @GetMapping("/login")
    public String loginForm(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(MemberConst.HOME_URI, homeUri);
        redirectAttributes.addAttribute(MemberConst.LOGIN_URI, homeUri + "/members/login/logic");
        return "redirect:" + memberUri + "/members/login";
    }

    @GetMapping("/login/logic")
    public String login(HttpServletRequest request,
                        @RequestParam(required = false) String homeUri,
                        @RequestParam(required = false) String loginId) {

        Optional<Member> loginMember = memberService.findByUserId(loginId);
        if (loginMember.isPresent()) {
            Member member = loginMember.get();
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

    @GetMapping("/profile")
    public String profile(HttpServletRequest request, RedirectAttributes redirectAttributes) {

        HttpSession session = request.getSession(false);
        MemberSessionDto memberSessionDto = (MemberSessionDto) session.getAttribute(MemberConst.MEMBER_SESSION_DTO);
        redirectAttributes.addAttribute("userId", memberSessionDto.getUserId());
        redirectAttributes.addAttribute(MemberConst.HOME_URI, homeUri);

        return "redirect:" + memberUri + "/members/{userId}/profile";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        MemberSessionDto memberSessionDto = (MemberSessionDto) session.getAttribute("memberSessionDto");
        if (session != null) {
            session.invalidate();
        }

        memberService.logout(memberSessionDto.getUserId());

        return "redirect:" + homeUri;
    }
}
