package com.zun;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class EurekaclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaclientApplication.class, args);
	}

	@Value("${server.port}")
	String port;

	@RequestMapping("/hi")
	public String home(@RequestParam String name) {
		return "Hi " + name + ", I am from port:" + port;
	}

	@RequestMapping("/login")
	public String login(@RequestParam String name, @RequestParam String password) {
		//判断用户名密码是否合法，合法否在进行token生成
		// 令牌有效期30天
		DateTime dt = new DateTime();
		Date expiration = dt.plusDays(30).toDate();
		// 生成令牌,参数可以自行组织
		String accessToken = Jwts.builder().setHeaderParam("alg", "HS256")
				.setHeaderParam("typ", "JWT")
				.claim("accountId", name)
				.claim("expTime", expiration)
				.signWith(SignatureAlgorithm.HS256, CoreConstants.SECRET).compact();
		return accessToken;
	}
}
