package HwangJiHun.poeitemvalues.controller;

import HwangJiHun.poeitemvalues.model.exception.MyException;
import HwangJiHun.poeitemvalues.model.ninja.dto.CardsDataListDto;
import HwangJiHun.poeitemvalues.service.ExceptionService;
import HwangJiHun.poeitemvalues.service.NinjaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.Timestamp;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @Value("${projectId}")
    private int projectId;

    @Autowired
    private final NinjaService ninjaService;
    @Autowired
    private final ExceptionService exceptionService;

    @GetMapping("/")
    public String home(Model model) throws IOException {

        CardsDataListDto cardsDataListDto = ninjaService.getTop5CardsData();

        model.addAttribute("cardsDataListDto", cardsDataListDto);

        return "/home";
    }

    @GetMapping("/ex")
    @ResponseBody
    public String exThrowTest() {
        String returnValue;
        try {
            throw new IllegalArgumentException("MyException");
        } catch (IllegalArgumentException e) {
            returnValue = exceptionService.sendExceptionToErrorCenter(e);

            log.info("GET returnValue = {}", returnValue);
        }



        return returnValue;
    }
}
