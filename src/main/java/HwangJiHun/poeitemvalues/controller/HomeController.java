package HwangJiHun.poeitemvalues.controller;

import HwangJiHun.poeitemvalues.model.ninja.dto.CardsDataListDto;
import HwangJiHun.poeitemvalues.repository.OverviewType;
import HwangJiHun.poeitemvalues.repository.ItemType;
import HwangJiHun.poeitemvalues.service.NinjaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private final NinjaService ninjaService;

    @GetMapping("/")
    public String home(Model model) throws IOException {

        CardsDataListDto currencyCardsDataListDto = ninjaService.getTop5CardsData(OverviewType.CURRENCYOVERVIEW.getApiEndPoint(), ItemType.CURRENCY.getTypeName());
        model.addAttribute("currencyCardsDataListDto", currencyCardsDataListDto);

        CardsDataListDto fragmentCardsDataListDto = ninjaService.getTop5CardsData(OverviewType.CURRENCYOVERVIEW.getApiEndPoint(), ItemType.Fragment.getTypeName());
        model.addAttribute("fragmentCardsDataListDto", fragmentCardsDataListDto);


        return "/home";
    }
}
