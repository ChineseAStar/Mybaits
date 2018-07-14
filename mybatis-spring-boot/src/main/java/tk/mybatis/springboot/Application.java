package tk.mybatis.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.springboot.mapper.CountryMapper;

/**
 * Spring Boot 启动类
 * @author 星星
 *
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CountryMapper countryMapper;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		countryMapper.selectAll();
	}

}
