package HwangJiHun.poeitemvalues.controller;

import HwangJiHun.poeitemvalues.domain.members.MemberConst;
import HwangJiHun.poeitemvalues.model.members.Member;
import HwangJiHun.poeitemvalues.model.ninja.dto.CardsDataListDto;
import HwangJiHun.poeitemvalues.repository.OverviewType;
import HwangJiHun.poeitemvalues.repository.ItemType;
import HwangJiHun.poeitemvalues.service.MemberService;
import HwangJiHun.poeitemvalues.service.NinjaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Controller
public class HomeController {

    private final NinjaService ninjaService;
    private final MemberService memberService;

    public HomeController(NinjaService ninjaService, MemberService memberService) {
        this.ninjaService = ninjaService;
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String home(Model model) throws IOException {

        CardsDataListDto currencyCardsDataListDto = ninjaService.getTop5CardsData(OverviewType.CURRENCYOVERVIEW.getApiEndPoint(), ItemType.CURRENCY.getTypeName());
        model.addAttribute("currencyCardsDataListDto", currencyCardsDataListDto);

        CardsDataListDto fragmentCardsDataListDto = ninjaService.getTop5CardsData(OverviewType.CURRENCYOVERVIEW.getApiEndPoint(), ItemType.Fragment.getTypeName());
        model.addAttribute("fragmentCardsDataListDto", fragmentCardsDataListDto);

        return "/home";
    }
}
