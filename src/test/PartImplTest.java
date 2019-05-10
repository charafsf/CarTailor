package test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cartaylor.Part;
import cartaylor.PartTypeImpl;
import cartaylor.Categories.EG100;
import cartaylor.Categories.Engine;


public class PartImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void testGetCategory() {
		Engine e = new Engine();
		PartTypeImpl pt = new PartTypeImpl(e, EG100.class);
		try {
			Part p = pt.newInstance();
			assertEquals(p.getPartType(),pt);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
