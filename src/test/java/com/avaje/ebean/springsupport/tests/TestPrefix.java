package com.avaje.ebean.springsupport.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import junit.framework.Assert;
import model.inheritance.CalculationResult;
import model.inheritance.Configurations;
import model.inheritance.GroupConfiguration;
import model.inheritance.ProductConfiguration;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Query;

@ContextConfiguration(locations={"/TestInheritance.xml"})
public class TestPrefix extends AbstractJUnit4SpringContextTests {

	@Autowired
	private EbeanServer ebeanServer;
	
	@Test
	public void testQueryApi() {

		List<GroupConfiguration> result = ebeanServer.find(GroupConfiguration.class).where().eq("name", "x").findList();
		
		assertTrue(result.isEmpty());
		
	}
	
}