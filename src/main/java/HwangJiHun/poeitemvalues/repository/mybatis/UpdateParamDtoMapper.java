package HwangJiHun.poeitemvalues.repository.mybatis;

import HwangJiHun.poeitemvalues.model.ninja.dto.database.UpdateParamDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UpdateParamDtoMapper {

    List<UpdateParamDto> findByCond(ItemSearchCond itemSearchCond);
}
