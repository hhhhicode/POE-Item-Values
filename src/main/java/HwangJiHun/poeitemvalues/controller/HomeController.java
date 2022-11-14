package HwangJiHun.poeitemvalues.controller;

import HwangJiHun.poeitemvalues.model.ninja.Currency;
import HwangJiHun.poeitemvalues.model.ninja.CurrencyDetail;
import HwangJiHun.poeitemvalues.model.ninja.CurrencyOverview;
import HwangJiHun.poeitemvalues.model.ninja.MarketPrice;
import HwangJiHun.poeitemvalues.model.ninja.dto.CurrencyOverviewDto;
import HwangJiHun.poeitemvalues.service.NinjaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final NinjaService ninjaService;

    @GetMapping("/")
    public String home(Model model) throws IOException {

        List<CurrencyOverviewDto> currencyOverviewDtos = new ArrayList<>();

        Map<Integer, String> currencyIdIconMap = new HashMap<>();
        Map<String, String> currencyNameIconMap = new HashMap<>();

        CurrencyOverview currencyOverview = ninjaService.getCurrencyOverview();
        List<Currency> lines = currencyOverview.getLines();
        List<CurrencyDetail> currencyDetails = currencyOverview.getCurrencyDetails();
        Map<Integer, CurrencyDetail> currencyDetailMap = new HashMap<>();
        for (CurrencyDetail currencyDetail : currencyDetails) {
            currencyDetailMap.put(currencyDetail.getId(), currencyDetail);
        }

        for (CurrencyDetail currencyDetail : currencyDetails) {
            currencyIdIconMap.put(currencyDetail.getId(), currencyDetail.getIcon());
            currencyNameIconMap.put(
                    currencyDetail.getName()
                            .toLowerCase()
                            .replace(' ', '-')
                            .replace("'", ""),
                    currencyDetail.getIcon()
            );
        }
        for (Currency line : lines) {
            MarketPrice pay = line.getPay();
            String currencyTypeName = line.getCurrencyTypeName();
            String detailsId = line.getDetailsId();
            Integer receivePayCurrencyId = line.getReceive().getPayCurrencyId();
            Integer receiveGetCurrencyId = line.getReceive().getGetCurrencyId();
            CurrencyDetail payCurrencyDetail = currencyDetailMap.get(receivePayCurrencyId);
            CurrencyDetail getCurrencyDetail = currencyDetailMap.get(receiveGetCurrencyId);
            Double receiveValue = line.getReceive().getValue();
            currencyOverviewDtos.add(new CurrencyOverviewDto
                    (
                            currencyTypeName,
                            currencyNameIconMap.get(detailsId),
                            payCurrencyDetail.getName(),
                            receiveValue >= 1000 ?
                                    (double) Math.round(receiveValue/1000*10)/10 + "k" :
                                    (double) Math.round(receiveValue*10)/10 + "",
                            payCurrencyDetail.getIcon(),
                            getCurrencyDetail.getName(),
                            getCurrencyDetail.getIcon()
                    )
            );
        }

        model.addAttribute("currencyOverviewDtos", currencyOverviewDtos);

        return "/home";
    }
}
