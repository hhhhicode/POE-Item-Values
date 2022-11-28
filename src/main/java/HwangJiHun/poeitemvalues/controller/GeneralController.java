package HwangJiHun.poeitemvalues.controller;

import HwangJiHun.poeitemvalues.model.ninja.dto.CurrencyOverviewDto;
import HwangJiHun.poeitemvalues.model.ninja.dto.DivinationCardOverviewDto;
import HwangJiHun.poeitemvalues.repository.mybatis.ItemSearchCond;
import HwangJiHun.poeitemvalues.service.NinjaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/general")
public class GeneralController {

    @Autowired
    NinjaService ninjaService;

    @GetMapping("/currency")
    public String currencyForm(Model model, @ModelAttribute("itemSearchCond")ItemSearchCond itemSearchCond) throws IOException {
        List<CurrencyOverviewDto> currencyOverviewDtoList = ninjaService.getCurrencyOverviewDtoList();

        model.addAttribute("currencyOverviewDtoList", currencyOverviewDtoList);
        log.info("itemSearchCond = {}", itemSearchCond);

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
