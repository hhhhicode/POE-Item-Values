package HwangJiHun.poeitemvalues.controller;

import HwangJiHun.poeitemvalues.model.ninja.dto.CurrencyOverviewDto;
import HwangJiHun.poeitemvalues.model.ninja.dto.DivinationCardOverviewDto;
import HwangJiHun.poeitemvalues.service.NinjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class GeneralController {

    @Autowired
    NinjaService ninjaService;

    @GetMapping("/general/currency")
    public String currency(Model model) throws IOException {
        List<CurrencyOverviewDto> currencyOverviewDtoList = ninjaService.getCurrencyOverview();

        model.addAttribute("currencyOverviewDtoList", currencyOverviewDtoList);

        return "/general/currency";
    }

    @GetMapping("/general/fragment")
    public String fragment(Model model) throws IOException {
        List<CurrencyOverviewDto> fragmentOverviewDtoList = ninjaService.getFragmentOverview();

        model.addAttribute("fragmentOverviewDtoList", fragmentOverviewDtoList);

        return "/general/fragment";
    }

    @GetMapping("/general/divinationcard")
    public String divinationcard(Model model) throws IOException {
        List<DivinationCardOverviewDto> divinationcardOverviewDtoList = ninjaService.getDivinationCardOverview();

        model.addAttribute("divinationcardOverviewDtoList", divinationcardOverviewDtoList);

        return "/general/divinationcard";
    }
}
