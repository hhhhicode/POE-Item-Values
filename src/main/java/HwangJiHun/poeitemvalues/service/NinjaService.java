package HwangJiHun.poeitemvalues.service;

import HwangJiHun.poeitemvalues.model.ninja.CurrencyOverview;
import HwangJiHun.poeitemvalues.repository.NinjaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class NinjaService {

    private final NinjaRepository ninjaRepository;

    public CurrencyOverview getCurrencyOverview() throws IOException {
        return ninjaRepository.getCurrencyOverview();
    }
}
