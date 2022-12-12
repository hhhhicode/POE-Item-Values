package HwangJiHun.poeitemvalues.controller.external;

import HwangJiHun.poeitemvalues.model.ninja.dto.CardsDataListDto;
import HwangJiHun.poeitemvalues.repository.ItemType;
import HwangJiHun.poeitemvalues.repository.OverviewType;
import HwangJiHun.poeitemvalues.service.NinjaService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/external")
public class ExternalController {

    private final NinjaService ninjaService;

    public ExternalController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @ResponseBody
    @GetMapping(value = "/currencies/currency/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
    public CardsDataListDto currencyDashboard() throws IOException {

        return ninjaService.getTop5CardsData(OverviewType.CURRENCYOVERVIEW.getApiEndPoint(), ItemType.CURRENCY.getTypeName());
    }

    @ResponseBody
    @GetMapping(value = "/currencies/fragment/dashboard")
    public CardsDataListDto fragmentDashboard() throws IOException {

        return ninjaService.getTop5CardsData(OverviewType.CURRENCYOVERVIEW.getApiEndPoint(), ItemType.Fragment.getTypeName());
    }
}
