package HwangJiHun.poeitemvalues.controller;

import HwangJiHun.poeitemvalues.domain.members.MemberConst;
import HwangJiHun.poeitemvalues.model.members.Member;
import HwangJiHun.poeitemvalues.model.ninja.dto.CurrencyOverviewDto;
import HwangJiHun.poeitemvalues.model.ninja.dto.DivinationCardOverviewDto;
import HwangJiHun.poeitemvalues.model.ninja.dto.database.PoeCurrencyDto;
import HwangJiHun.poeitemvalues.repository.mybatis.ItemSearchCond;
import HwangJiHun.poeitemvalues.service.MemberService;
import HwangJiHun.poeitemvalues.service.NinjaService;
import HwangJiHun.poeitemvalues.service.PoeCurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/general")
public class GeneralController {

    private final NinjaService ninjaService;
    private final PoeCurrencyService poeCurrencyService;
    private final MemberService memberService;

    public GeneralController(NinjaService ninjaService, PoeCurrencyService poeCurrencyService, MemberService memberService) {
        this.ninjaService = ninjaService;
        this.poeCurrencyService = poeCurrencyService;
        this.memberService = memberService;
    }

    @GetMapping("/currency")
    public String currencyForm(Model model, @ModelAttribute("itemSearchCond")ItemSearchCond itemSearchCond) {
        List<PoeCurrencyDto> poeCurrencyDtoList = poeCurrencyService.findCond(itemSearchCond);
        List<CurrencyOverviewDto> currencyOverviewDtoList = poeCurrencyService.poeCurrencyDtoListToCurrencyOverviewDtoList(poeCurrencyDtoList);

        model.addAttribute("currencyOverviewDtoList", currencyOverviewDtoList);

        return "/general/currency";
    }

    @GetMapping("/fragment")
    public String fragment(Model model) throws IOException {
        List<CurrencyOverviewDto> fragmentOverviewDtoList = ninjaService.getFragmentOverviewDtoList();

        model.addAttribute("fragmentOverviewDtoList", fragmentOverviewDtoList);

        return "/general/fragment";
    }

    @GetMapping("/divinationcard")
    public String divinationcard(Model model) throws IOException {
        List<DivinationCardOverviewDto> divinationcardOverviewDtoList = ninjaService.getDivinationCardOverviewDtoList();

        model.addAttribute("divinationcardOverviewDtoList", divinationcardOverviewDtoList);

        return "/general/divinationcard";
    }
}
