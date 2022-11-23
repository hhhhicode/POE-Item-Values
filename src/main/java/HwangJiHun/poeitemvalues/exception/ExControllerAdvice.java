package HwangJiHun.poeitemvalues.exception;

import HwangJiHun.poeitemvalues.controller.GeneralController;
import HwangJiHun.poeitemvalues.service.ExceptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ExceptionInterceptor 와
 *  ExControllerAdvice 둘 중 어느걸 사용해야 하는가...
 * ExControllerAdvice 는 API Error 에 사용해야 한다.
 *
 * API Exception 이 터지면, DB에 담아두기.
 *      JdbcTemplate, MyBatis 사용.
 * 담아둔건 JSON 으로 제공하기.
 */
@Slf4j
@RestControllerAdvice(assignableTypes = {GeneralController.class})
public class ExControllerAdvice {

    @Autowired
    ExceptionService exceptionService;

    @ExceptionHandler
    public String myExHandle(Exception e) {
        log.info("MyException e = ", e);

        return e.toString();
    }
}