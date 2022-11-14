package HwangJiHun.poeitemvalues.controller;

import HwangJiHun.poeitemvalues.model.ninja.CurrencyOverview;
import HwangJiHun.poeitemvalues.service.NinjaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final NinjaService ninjaService;

    @GetMapping("/")
    public String home() throws IOException {

        return "/home";
    }
}
