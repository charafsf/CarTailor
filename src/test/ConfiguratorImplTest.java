package test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cartaylor.CompatibilityManagerImpl;
import cartaylor.ConfiguratorImpl;
import cartaylor.ConflictingRuleException;
import cartaylor.Part;
import cartaylor.PartType;
import cartaylor.PartTypeImpl;
import cartaylor.Categories.*;
public class ConfiguratorImplTest {
	static ConfiguratorImpl configuratorImpl;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		configuratorImpl = new ConfiguratorImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConfiguratorImpl() {
		ConfiguratorImpl conf;
		conf = new ConfiguratorImpl();
		assertNotNull(conf);
	}

	@Test
	public void testGetSelectionForCategory() {
		ConfiguratorImpl conf = new ConfiguratorImpl();
		Engine e = new Engine();
		Transmission t = new Transmission();
		conf.getCategories().add(e);
		conf.getCategories().add(t);
		PartTypeImpl pt = new PartTypeImpl(e, EG100.class);
		try {
			Part p = pt.newInstance();
			conf.getConfigurationList().add(p);
			assertNotNull(conf.getSelectionForCategory(e));
			assertEquals(p,conf.getSelectionForCategory(e));
			assertNull(conf.getSelectionForCategory(t));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}

	@Test
	public void testIsComplete() {
		ConfiguratorImpl conf = new ConfiguratorImpl();
		ConfiguratorImpl conf2 = new ConfiguratorImpl();
		//categories
		Engine e = new Engine();
		Transmission t = new Transmission();
		conf.getCategories().add(e);
		conf.getCategories().add(t);
		
		conf2.getCategories().add(e);
		conf2.getCategories().add(t);
		//partTypes
		PartTypeImpl pt = new PartTypeImpl(e, EG100.class);
		PartTypeImpl pt2 = new PartTypeImpl(t, TA5.class);
		
		PartTypeImpl pt3 = new PartTypeImpl(e, EG100.class);
		try {
			conf.getConfigurationList().add(pt.newInstance());
			conf.getConfigurationList().add(pt2.newInstance());
			conf2.getConfigurationList().add(pt3.newInstance());
			assertTrue(conf.isComplete());
			assertFalse(conf2.isComplete());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}

	@Test
	public void testIsValid() {
		ConfiguratorImpl conf = new ConfiguratorImpl();
		CompatibilityManagerImpl cm = conf.getCompatibilityManager();
		//categories
		Engine e = new Engine();
		Transmission t = new Transmission();
		Exterior ext = new Exterior();
		Interior in = new Interior();
		conf.getCategories().add(e);
		conf.getCategories().add(t);
		conf.getCategories().add(ext);
		conf.getCategories().add(in);
		//partTypes
		PartTypeImpl pte1 = new PartTypeImpl(e, EG100.class);
		PartTypeImpl pte2 = new PartTypeImpl(e, EG133.class);
		PartTypeImpl pte3 = new PartTypeImpl(e, EG210.class);
		PartTypeImpl pte4 = new PartTypeImpl(e, ED110.class);
		PartTypeImpl pte5 = new PartTypeImpl(e, ED180.class);
		PartTypeImpl pte6 = new PartTypeImpl(e, EH120.class);
		PartTypeImpl ptt1 = new PartTypeImpl(t, TM5.class);
		PartTypeImpl ptt2 = new PartTypeImpl(t, TM6.class);
		PartTypeImpl ptt3 = new PartTypeImpl(t, TA5.class);
		PartTypeImpl ptt4 = new PartTypeImpl(t, TS6.class);
		PartTypeImpl ptt5 = new PartTypeImpl(t, TSF7.class);
		PartTypeImpl ptt6 = new PartTypeImpl(t, TC120.class);
		PartTypeImpl ptex1 = new PartTypeImpl(ext, XC.class);
		PartTypeImpl ptex2 = new PartTypeImpl(ext, XM.class);
		PartTypeImpl ptex3 = new PartTypeImpl(ext, XS.class);
		PartTypeImpl ptin1 = new PartTypeImpl(in, IN.class);
		PartTypeImpl ptin2 = new PartTypeImpl(in, IH.class);
		PartTypeImpl ptin3 = new PartTypeImpl(in, IS.class);
		//incompatibilities
		Collection<PartType> inc1 = new ArrayList<>();
		Collection<PartType> inc2 = new ArrayList<>();
		Collection<PartType> inc3 = new ArrayList<>();
		Collection<PartType> inc4 = new ArrayList<>();
		Collection<PartType> inc5 = new ArrayList<>();
		Collection<PartType> inc6 = new ArrayList<>();
		inc1.add(pte1);
		inc2.add(pte1);
		inc2.add(pte2);
		inc2.add(pte4);
		inc3.add(pte3);
		inc4.add(pte1);
		inc5.add(pte1);
		inc6.add(pte1);
		inc6.add(ptt1);
		//requirements
		Collection<PartType> req1 = new ArrayList<>();
		Collection<PartType> req2 = new ArrayList<>();
		Collection<PartType> req3 = new ArrayList<>();
		Collection<PartType> req4 = new ArrayList<>();
		req1.add(ptt6);
		req2.add(pte6);
		req3.add(ptin3);
		req1.add(ptex3);
		try {
			//add incompatibilities
			cm.addIncompatibilities(ptt3, inc1);
			cm.addIncompatibilities(ptt5, inc2);
			cm.addIncompatibilities(ptex1, inc3);
			cm.addIncompatibilities(ptex2, inc4);
			cm.addIncompatibilities(ptex3, inc5);
			cm.addIncompatibilities(ptin3, inc6);
			//add requirements
			cm.addRequirements(pte6, req1);
			cm.addRequirements(ptt6, req2);
			cm.addRequirements(ptex3, req3);
			cm.addRequirements(ptin3, req4);
			//test1
			conf.selectPart(pte1);
			conf.selectPart(ptt2);
			conf.selectPart(ptex1);
			conf.selectPart(ptin1);
			assertTrue(conf.isValid());
			//test2 incompatibilities
			conf = new ConfiguratorImpl();
			CompatibilityManagerImpl cm2 = conf.getCompatibilityManager();
			//add incompatibilities
			cm2.addIncompatibilities(ptt3, inc1);
			cm2.addIncompatibilities(ptt5, inc2);
			cm2.addIncompatibilities(ptex1, inc3);
			cm2.addIncompatibilities(ptex2, inc4);
			cm2.addIncompatibilities(ptex3, inc5);
			cm2.addIncompatibilities(ptin3, inc6);
			//add requirements
			cm2.addRequirements(pte6, req1);
			cm2.addRequirements(ptt6, req2);
			cm2.addRequirements(ptex3, req3);
			cm2.addRequirements(ptin3, req4);
			conf.selectPart(pte1);
			conf.selectPart(ptt1);
			conf.selectPart(ptex2);
			conf.selectPart(ptin2);
			assertFalse(conf.isValid());
			//test3 requirements
			conf = new ConfiguratorImpl();
			CompatibilityManagerImpl cm3 = conf.getCompatibilityManager();
			//add incompatibilities
			cm3.addIncompatibilities(ptt3, inc1);
			cm3.addIncompatibilities(ptt5, inc2);
			cm3.addIncompatibilities(ptex1, inc3);
			cm3.addIncompatibilities(ptex2, inc4);
			cm3.addIncompatibilities(ptex3, inc5);
			cm3.addIncompatibilities(ptin3, inc6);
			//add requirements
			cm3.addRequirements(pte6, req1);
			cm3.addRequirements(ptt6, req2);
			cm3.addRequirements(ptex3, req3);
			cm3.addRequirements(ptin3, req4);
			conf.selectPart(pte6);
			conf.selectPart(ptt4);
			conf.selectPart(ptex3);
			conf.selectPart(ptin3);
			assertFalse(conf.isValid());
		} catch (ConflictingRuleException e1) {
			e1.printStackTrace();
		}
		
	}

	@Test
	public void testSelectPart() {
		ConfiguratorImpl conf = new ConfiguratorImpl();
		//categories
		Engine e = new Engine();
		Transmission t = new Transmission();
		conf.getCategories().add(e);
		conf.getCategories().add(t);
		//partTypes
		PartTypeImpl pt = new PartTypeImpl(e, EG100.class);
		PartTypeImpl pt2 = new PartTypeImpl(t, TA5.class);
		PartTypeImpl pt3 = new PartTypeImpl(e, ED180.class);
		int size = conf.getConfigurationList().size();
		conf.selectPart(pt);
		conf.selectPart(pt2);
		assertNotEquals(conf.getConfigurationList().size(),size);
		conf.selectPart(pt3);
		assertNotEquals(conf.getSelectionForCategory(e).getPartType(),pt);
		assertEquals(conf.getSelectionForCategory(e).getPartType(),pt3);
	}


	@Test
	public void testGetVariantsForCategory() {
		ConfiguratorImpl conf = new ConfiguratorImpl();
		//categories
		Engine e = new Engine();
		Transmission t = new Transmission();
		conf.getCategories().add(e);
		conf.getCategories().add(t);
		//partTypes
		PartTypeImpl pt = new PartTypeImpl(e, EG100.class);
		PartTypeImpl pt2 = new PartTypeImpl(t, TA5.class);
		PartTypeImpl pt3 = new PartTypeImpl(e, ED180.class);
		ArrayList<PartType> testList = new ArrayList<>();
		testList.add(pt);
		testList.add(pt3);
		conf.getPartTypes().add(pt);
		conf.getPartTypes().add(pt2);
		conf.getPartTypes().add(pt3);
		assertEquals(testList,conf.getVariantsForCategory(e));
	}


}
