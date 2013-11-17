package com.github.cmled.springnettytemplate.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

import com.github.cmled.springnettytemplate.controller.StaticFilesController;
import com.github.cmled.springnettytemplate.dao.EntityManagerUtil;

public class Main {

	public static void main(String[] args) throws Exception {

		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"root-context.xml");
		EntityManagerUtil.initEntityManager(); // Initialize entityManager for
												// the app
		Assert.notNull(ac);
		Assert.notNull(ac.getBean(StaticFilesController.class));

		NettyServer netty = ac.getBean(NettyServer.class);

		netty.start();

	}
}
