package HwangJiHun.poeitemvalues.controller;

import HwangJiHun.poeitemvalues.model.ninja.dto.CurrencyOverviewDto;
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
    public String home() {


        return "/home";
    }

    @GetMapping("/general/currency")
    public String currency(Model model) throws IOException {
        List<CurrencyOverviewDto> currencyOverviewDtoList = ninjaService.getCurrencyOverview();

        model.addAttribute("currencyOverviewDtoList", currencyOverviewDtoList);

        return "/general/Currency";
    }
}
