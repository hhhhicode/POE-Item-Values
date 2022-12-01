package HwangJiHun.poeitemvalues.service;

import HwangJiHun.poeitemvalues.domain.feign.ExceptionThrowClient;
import HwangJiHun.poeitemvalues.model.exception.MyException;
import HwangJiHun.poeitemvalues.model.exception.MyExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExceptionService {

    private final ExceptionThrowClient exceptionThrowClient;
    private final MyExceptionUtils myExceptionUtils;

    public ExceptionService(ExceptionThrowClient exceptionThrowClient, MyExceptionUtils myExceptionUtils) {
        this.exceptionThrowClient = exceptionThrowClient;
        this.myExceptionUtils = myExceptionUtils;
    }

    /**
     * MyException을 지정한 Exception Center로 보냅니다.
     */
    public String sendExceptionToErrorCenter(Exception e) {
        MyException myException = myExceptionUtils.getMyException(e);
        String returnValue = exceptionThrowClient.postThrowException(myException);

        return returnValue;
    }
}
