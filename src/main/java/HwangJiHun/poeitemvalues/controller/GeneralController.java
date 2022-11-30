package HwangJiHun.poeitemvalues.controller;

import HwangJiHun.poeitemvalues.model.ninja.dto.CurrencyOverviewDto;
import HwangJiHun.poeitemvalues.model.ninja.dto.DivinationCardOverviewDto;
import HwangJiHun.poeitemvalues.model.ninja.dto.database.PoeCurrencyDto;
import HwangJiHun.poeitemvalues.repository.mybatis.ItemSearchCond;
import HwangJiHun.poeitemvalues.service.NinjaService;
import HwangJiHun.poeitemvalues.service.PoeCurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/general")
public class GeneralController {

    private final NinjaService ninjaService;
    private final PoeCurrencyService poeCurrencyService;

    public GeneralController(NinjaService ninjaService, PoeCurrencyService poeCurrencyService) {
        this.ninjaService = ninjaService;
        this.poeCurrencyService = poeCurrencyService;
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
