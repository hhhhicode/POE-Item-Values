package HwangJiHun.poeitemvalues.service;

import HwangJiHun.poeitemvalues.model.ninja.dto.database.CurrencyDetailsDto;
import HwangJiHun.poeitemvalues.repository.CurrencyDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceDbService {

    private final CurrencyDetailsRepository currencyDetailsRepository;

    public ResourceDbService(CurrencyDetailsRepository currencyDetailsRepository) {
        this.currencyDetailsRepository = currencyDetailsRepository;
    }

    public List<CurrencyDetailsDto> findAll() {
        return currencyDetailsRepository.findAll();
    }

    public int saveCurrencyDetails(CurrencyDetailsDto dto) {
        return currencyDetailsRepository.save(dto);
    }
    public int deleteAllCurrencyDetails() {
        return currencyDetailsRepository.deleteAll();
    }
}
