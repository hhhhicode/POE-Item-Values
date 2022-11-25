package HwangJiHun.poeitemvalues.controller;

import HwangJiHun.poeitemvalues.model.ninja.dto.CurrencyOverviewDto;
import HwangJiHun.poeitemvalues.model.ninja.dto.DivinationCardOverviewDto;
import HwangJiHun.poeitemvalues.service.NinjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/general")
public class GeneralController {

    @Autowired
    NinjaService ninjaService;

    @GetMapping("/currency")
    public String currency(Model model) throws IOException {
        List<CurrencyOverviewDto> currencyOverviewDtoList = ninjaService.getCurrencyOverviewDtoList();

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
