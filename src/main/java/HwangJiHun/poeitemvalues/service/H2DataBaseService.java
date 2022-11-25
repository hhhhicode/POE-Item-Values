package HwangJiHun.poeitemvalues.service;

import HwangJiHun.poeitemvalues.model.ninja.Currency;
import HwangJiHun.poeitemvalues.model.ninja.dto.database.UpdateParamDto;
import HwangJiHun.poeitemvalues.repository.H2DataBaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class H2DataBaseService {

    private final H2DataBaseRepository repository;

    public H2DataBaseService(H2DataBaseRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void currencySave(Currency currency) {
        UpdateParamDto updateParamDto = repository.transferCurrencyToUpdateParamDto(currency);
        repository.save(updateParamDto);
    }

    public List<UpdateParamDto> findAll() {
        return repository.findAll();
    }
}
