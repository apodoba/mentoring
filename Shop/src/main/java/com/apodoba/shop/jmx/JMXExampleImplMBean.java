package com.apodoba.shop.jmx;

import java.util.Random;

public class JMXExampleImplMBean implements JMXExampleMBean {

	private String projectName;

	public JMXExampleImplMBean(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public String projectName() {
		return this.projectName;
	}

	@Override
	public int randomNumber() {
		return (new Random()).nextInt(50) + 1;
	}

}