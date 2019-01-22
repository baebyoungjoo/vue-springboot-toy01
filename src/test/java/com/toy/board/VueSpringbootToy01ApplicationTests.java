package com.toy.board;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:./application.properties")
public class VueSpringbootToy01ApplicationTests {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${spring.datasource.driver-class-name}") private String DRIVER;
	@Value("${spring.datasource.url}") private String URL;
	@Value("${spring.datasource.username}") private String USERNAME;
	@Value("${spring.datasource.password}") private String PASSWORD;
	private String sql = "show databases";
	
	@Test
	public void contextLoads() throws Exception {
		Class.forName(DRIVER);
		
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			log.info("======================OK======================");
			log.info(" " + con.toString() + " ");
			log.info(" " + con.prepareStatement(sql).execute() + " ");
			log.info("==============================================");
		} catch(Exception e) {
			log.debug(e.getMessage());
		}
	}
}

