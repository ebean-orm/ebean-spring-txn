package com.avaje.ebean.springsupport.tests;

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
public class TestInheritance extends AbstractJUnit4SpringContextTests {

	@Autowired
	private EbeanServer ebeanServer;
	
	@Test
	public void testAssocOne() {

		final ProductConfiguration pc = new ProductConfiguration();
		pc.setName("PC1");
		ebeanServer.save(pc);

		final GroupConfiguration gc = new GroupConfiguration();
		gc.setName("GC1");
		ebeanServer.save(gc);

		CalculationResult r = new CalculationResult();
		final Double charge = 100.0;
		r.setCharge(charge);
		r.setProductConfiguration(pc);
		r.setGroupConfiguration(gc);
		ebeanServer.save(r);


		Query<CalculationResult> q = ebeanServer.createNamedQuery(CalculationResult.class, "loadResult");
		q.setParameter("charge", charge);
		
		List<CalculationResult> results = q.findList();
		
		Assert.assertTrue(!results.isEmpty());
	}
	
	@Test
	public void testAssocMany() {
		Configurations configurations = new Configurations();
		
		ebeanServer.save(configurations);


		final GroupConfiguration gc = new GroupConfiguration("GC1");
		configurations.add(gc);
		
		
		ebeanServer.save(gc);
		
		
		Configurations configurationsQueried = ebeanServer.find(Configurations.class, configurations.getId());
		
		List<GroupConfiguration> groups = configurationsQueried.getGroupConfigurations();
		
		Assert.assertTrue(!groups.isEmpty());
	}
}