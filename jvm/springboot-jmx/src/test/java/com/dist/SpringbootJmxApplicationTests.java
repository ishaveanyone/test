package com.dist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJmxApplicationTests {

	@Autowired
	@Qualifier(value="AbstractServiceImpl1")
	AbstractService abstractServiceImpl1;


	@Autowired
	@Qualifier(value ="AbstractServiceImpl2" )
	AbstractService abstractServiceImpl2;


	@Test
	public void contextLoads() {
		abstractServiceImpl1.test();
	}

}
