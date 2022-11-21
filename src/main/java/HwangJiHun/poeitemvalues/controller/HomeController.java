package HwangJiHun.poeitemvalues.controller;

import HwangJiHun.poeitemvalues.model.ninja.dto.CardsDataListDto;
import HwangJiHun.poeitemvalues.model.ninja.dto.OverviewDto;
import HwangJiHun.poeitemvalues.service.NinjaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final NinjaService ninjaService;

    @GetMapping("/")
    public String home(Model model) throws IOException {

        CardsDataListDto cardsDataListDto = ninjaService.getTop5CardsData();

        model.addAttribute("top5CardsDataList", cardsDataListDto.getTop5Cards());
        model.addAttribute("bottom5CardsDataList", cardsDataListDto.getBottom5Cards());

        return "/home";
    }

    @GetMapping("/general/currency")
    public String currency(Model model) throws IOException {
        List<OverviewDto> currencyOverviewDtoList = ninjaService.getCurrencyOverview();

        model.addAttribute("currencyOverviewDtoList", currencyOverviewDtoList);

        return "/Currency";
    }

    @GetMapping("/general/fragment")
    public String fragment(Model model) throws IOException {
        List<OverviewDto> fragmentOverviewDtoList = ninjaService.getFragmentOverview();

        model.addAttribute("fragmentOverviewDtoList", fragmentOverviewDtoList);

        return "/Fragment";
    }
}
