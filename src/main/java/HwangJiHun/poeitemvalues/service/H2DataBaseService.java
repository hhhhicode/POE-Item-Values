package HwangJiHun.poeitemvalues.service;

import HwangJiHun.poeitemvalues.model.ninja.Currency;
import HwangJiHun.poeitemvalues.model.ninja.dto.database.UpdateParamDto;
import HwangJiHun.poeitemvalues.repository.H2DataBaseRepository;
import HwangJiHun.poeitemvalues.repository.ItemType;
import HwangJiHun.poeitemvalues.repository.mybatis.ItemSearchCond;
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
    public void currencySave(Currency currency, String currenyType) {
        UpdateParamDto updateParamDto = repository.transferCurrencyToUpdateParamDto(currency, currenyType);
        repository.save(updateParamDto);
    }

    public List<UpdateParamDto> findAll() {
        return repository.findAll();
    }

    public List<UpdateParamDto> findByItemType(String itemType) {
        return repository.findByItemType(itemType);
    }

    public List<UpdateParamDto> findByCond(ItemSearchCond cond) {
        return repository.findByCond(cond);
    }
}
