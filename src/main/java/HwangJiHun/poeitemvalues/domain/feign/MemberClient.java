package HwangJiHun.poeitemvalues.domain.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "memberClient", url = "http://localhost:8091")
public interface MemberClient {

    @GetMapping(value = "/members/add")
    String addClient();
}
