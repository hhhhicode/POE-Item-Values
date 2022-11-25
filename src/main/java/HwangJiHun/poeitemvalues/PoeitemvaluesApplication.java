package HwangJiHun.poeitemvalues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PoeitemvaluesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoeitemvaluesApplication.class, args);
	}
}

//TODO Home 화면에 fragment max min cards 추가
//TODO Home 화면에 divination card max min cards 추가
