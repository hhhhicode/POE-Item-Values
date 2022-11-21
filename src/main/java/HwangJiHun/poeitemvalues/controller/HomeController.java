package HwangJiHun.poeitemvalues.controller;

import HwangJiHun.poeitemvalues.model.ninja.dto.CardsDataListDto;
import HwangJiHun.poeitemvalues.service.NinjaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final NinjaService ninjaService;

    @GetMapping("/")
    public String home(Model model) throws IOException {

        CardsDataListDto cardsDataListDto = ninjaService.getTop5CardsData();

        model.addAttribute("cardsDataListDto", cardsDataListDto);

        return "/home";
    }
}
