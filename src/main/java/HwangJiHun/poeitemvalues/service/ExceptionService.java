package HwangJiHun.poeitemvalues.service;

import HwangJiHun.poeitemvalues.domain.feign.ExceptionThrowClient;
import HwangJiHun.poeitemvalues.model.exception.MyException;
import HwangJiHun.poeitemvalues.model.exception.MyExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExceptionService {

    @Autowired
    ExceptionThrowClient exceptionThrowClient;

    /**
     * MyException을 지정한 Exception Center로 보냅니다.
     */
    public String sendExceptionToErrorCenter(Exception e) {
        MyExceptionUtils myExceptionUtils = new MyExceptionUtils();
        MyException myException = myExceptionUtils.getMyException(e);
        String returnValue = exceptionThrowClient.postThrowException(myException);

        return returnValue;
    }
}
