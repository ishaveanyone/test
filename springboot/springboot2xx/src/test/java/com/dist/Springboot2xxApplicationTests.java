package com.dist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot2xxApplicationTests {
	@Autowired
	XmlService xmlService;
	@Autowired
	ExitService exitService;
	@Test
	public void contextLoads() throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					xmlService.getTestClassName();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		Thread.sleep(3000);

//		exitService.exit();
	}


	public static void main(String[] args) {
		Queue queue=new q
	}
}


