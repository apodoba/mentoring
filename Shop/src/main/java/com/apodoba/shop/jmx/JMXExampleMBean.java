package com.apodoba.shop.jmx;

import javax.management.MXBean;

@MXBean
public interface JMXExampleMBean {

	public String projectName();
	
	public int randomNumber();
}