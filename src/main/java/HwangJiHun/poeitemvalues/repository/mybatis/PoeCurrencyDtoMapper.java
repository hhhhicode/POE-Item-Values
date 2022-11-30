package HwangJiHun.poeitemvalues.repository.mybatis;

import HwangJiHun.poeitemvalues.model.ninja.dto.database.PoeCurrencyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PoeCurrencyDtoMapper {

    List<PoeCurrencyDto> findByCond(ItemSearchCond itemSearchCond);
}
