package test;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cartaylor.Part;
import cartaylor.PartTypeImpl;
import cartaylor.Categories.*;

public class PartTypeImplTest {

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
	public void test() {
		Engine e = new Engine();
		PartTypeImpl pt = null;
		pt = new PartTypeImpl(e, EG100.class);
		assertNotNull(pt);
	}
	
	
	@Test
	public void testNewInstance() {
		Engine e = new Engine();
		PartTypeImpl pt = new PartTypeImpl(e, EG100.class);
		Part p = null;
		try {
			p = pt.newInstance();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			assertNotNull(p);
		}
	}
	
	@Test
	public void testGetName() {
		Engine e = new Engine();
		PartTypeImpl pt = new PartTypeImpl(e, EG100.class);
		PartTypeImpl pt2 = new PartTypeImpl(e, ED180.class);
		assertEquals("cartaylor.Categories.EG100",pt.getName());
		assertEquals("cartaylor.Categories.ED180",pt2.getName());
	}
	
	@Test
	public void testGetCategory() {
		Engine e = new Engine();
		PartTypeImpl pt = new PartTypeImpl(e, EG100.class);
		assertEquals(e,pt.getCategory());
	}
}
