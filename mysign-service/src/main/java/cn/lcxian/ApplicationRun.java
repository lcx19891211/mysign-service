package cn.lcxian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("cn.lcxian.dao")
public class ApplicationRun {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationRun.class, args);
	}
}
