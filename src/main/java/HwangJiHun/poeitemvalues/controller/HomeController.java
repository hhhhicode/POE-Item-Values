package HwangJiHun.poeitemvalues.controller;

import HwangJiHun.poeitemvalues.model.ninja.*;
import HwangJiHun.poeitemvalues.model.ninja.Currency;
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
import java.util.*;

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
            MarketPrice receive = line.getReceive();
            MarketPrice pay = line.getPay();

            String currencyTypeName = line.getCurrencyTypeName();
            String detailsId = line.getDetailsId();

            Integer receivePayCurrencyId = receive.getPayCurrencyId();
            Integer receiveGetCurrencyId = receive.getGetCurrencyId();
            Integer payPayCurrencyId = null;
            Integer payGetCurrencyId = null;
            if (pay != null) {
                payPayCurrencyId = pay.getPayCurrencyId();
                payGetCurrencyId = pay.getGetCurrencyId();
            }


            /*Currency Detail*/
            CurrencyDetail buyPayCurrencyDetail = currencyDetailMap.get(receivePayCurrencyId);
            CurrencyDetail buyGetCurrencyDetail = currencyDetailMap.get(receiveGetCurrencyId);
            CurrencyDetail sellPayCurrencyDetail = null;
            CurrencyDetail sellGetCurrencyDetail = null;
            if (pay != null) {
                sellPayCurrencyDetail = currencyDetailMap.get(payPayCurrencyId);
                sellGetCurrencyDetail = currencyDetailMap.get(payGetCurrencyId);
            }


            SparkLine buySparkLine = line.getReceiveSparkLine();
            SparkLine sellSparkLine = line.getPaySparkLine();
            Integer buySparkLineTotalChange = Math.toIntExact(Math.round(buySparkLine.getTotalChange()));
            Integer sellSparkLineTotalChange = Math.toIntExact(Math.round(sellSparkLine.getTotalChange()));

            Double buyValue = receive.getValue();
            Double sellValue = null;
            if (pay != null) {
                sellValue = 1 / pay.getValue();
            }

            currencyOverviewDtos.add(new CurrencyOverviewDto
                    (
                            /*Name*/
                            currencyTypeName,
                            currencyNameIconMap.get(detailsId),
                            buyPayCurrencyDetail.getName(),

                            /*Buying Price*/
                            buyPayCurrencyDetail.getName(),
                            buyValue >= 1000 ?
                                    (double) Math.round(buyValue/1000*10)/10 + "k" :
                                    String.valueOf((double) (
                                            Math.round(buyValue*10)/10 >= 1 ?
                                                    Math.round(buyValue*10)/10 :
                                                    1
                                            )
                                    ),
                            buyPayCurrencyDetail.getIcon(),

                            buyGetCurrencyDetail.getName(),
                            buyValue >= 1 ?
                                    String.valueOf(1.0) :
                                    String.valueOf((double) Math.round(1 / buyValue*10)/10),
                            buyGetCurrencyDetail.getIcon(),

                            /*Buying Price Last 7 days*/
                            buySparkLineTotalChange > 0 ?
                                    "+" + buySparkLineTotalChange + "%" :
                                    buySparkLineTotalChange + "%",

                            /*Selling Price*/
                            sellPayCurrencyDetail != null ? sellPayCurrencyDetail.getName() : null,
                            sellValue != null ?
                                    (
                                            sellValue >= 1 ?
                                                    String.valueOf(1.0) :
                                                    String.valueOf((double) Math.round(1 / sellValue*10)/10)
                                    ) :
                                    null,
                            sellPayCurrencyDetail != null ? sellPayCurrencyDetail.getIcon() : null,

                            sellGetCurrencyDetail != null ? sellGetCurrencyDetail.getName() : null,
                            sellValue != null ?
                                    (
                                            sellValue >= 1000 ?
                                                    (double) Math.round(sellValue/1000*10)/10 + "k" :
                                                    String.valueOf((double) Math.round(sellValue*10)/10)
                                    ) :
                                    null,
                            sellGetCurrencyDetail != null ? sellGetCurrencyDetail.getIcon() : null,

                            /*Buying Price Last 7 days*/
                            sellSparkLineTotalChange > 0 ?
                                    "+" + sellSparkLineTotalChange + "%" :
                                    sellSparkLineTotalChange + "%"
                    )
            );

            Double svgWidth = 58d;
            Double svgHeight = 20d;
            Double svgSize = 18d;
            List<Double> svgDataList = buySparkLine.getData();
            List<Double> convertSvgDataList = new ArrayList<>();
            List<Double> xList = new ArrayList<>();
            List<Double> yList = new ArrayList<>();
            Double intervalX = svgWidth / svgSize;
            Double valueRange = svgDataList.stream().max(Double::compareTo).get() - svgDataList.stream().min(Double::compareTo).get();
            for (Double svgData : svgDataList) {
                convertSvgDataList.add(svgData * 22 / valueRange);
            }

            for (int i = 1; i < convertSvgDataList.size(); i++) {
                for (int j = 1; j <= 3; j++) {
                    Double diffHeight = convertSvgDataList.get(i) - convertSvgDataList.get(i - 1);
                    yList.add(convertSvgDataList.get(i - 1) + diffHeight / 3 * j);
                }
            }
            for (int i = 0; i < svgSize; i++) {
                xList.add(i * intervalX);
            }

            //TODO Y에 마이너스 값이 있다. 마이너스가 있으면 안된다.
        }

        model.addAttribute("currencyOverviewDtos", currencyOverviewDtos);

        return "/home";
    }
}
