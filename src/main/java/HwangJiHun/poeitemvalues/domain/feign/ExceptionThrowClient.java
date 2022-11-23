package HwangJiHun.poeitemvalues.domain.feign;

import HwangJiHun.poeitemvalues.model.exception.MyException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "throwException", url = "http://localhost:8090")
public interface ExceptionThrowClient {

    @PostMapping(value = "/ex/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    String postThrowException(MyException e);
}
