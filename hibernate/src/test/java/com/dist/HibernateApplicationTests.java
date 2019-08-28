package com.dist;

import com.dist.Subselect.CombineService;
import com.dist.Subselect.DateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HibernateApplicationTests {
	@Autowired
	private CombineService combineService;

	@Autowired
	private DateService dateService;
	@Test
	public void contextLoads1() {
		combineService.getAllCombine().forEach(System.out::println);
	}
	@Test
	public void contextLoads2() {
		combineService.getAllView().forEach(System.out::println);
	}
	@Test
	public void dateService() {
		System.out.println(dateService.getDateType());
	}

}
