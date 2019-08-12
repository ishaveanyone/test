package com.dist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = MockitoApplication.class)
@WebAppConfiguration
public class MockitoApplicationTests {

	@Autowired
	@InjectMocks//被注入mock对象的类一般是被测试类
	UserServiceA userServiceA ;

	@Mock//需要mock的bean
	private RoleServiceA roleServiceA;

	@Before
	public void setUp() throws Exception {
//		Mockito.when(roleServiceA.findOneRole()).thenReturn(new Role("name"));
	}

	@Test
	public void test() {
		// 和上面的before 2选1

		// when(actionService.doSay(anyString())).thenReturn("helloworld");
		Mockito.when(roleServiceA.findOneRole()).thenReturn(new Role("name"));

		System.out.println(userServiceA.findOneUser());

		verify(roleServiceA).findOneRole();

	}


}
