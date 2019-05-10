package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cartaylor.CompatibilityManagerImpl;
import cartaylor.ConflictingRuleException;
import cartaylor.PartType;
import cartaylor.PartTypeImpl;
import cartaylor.Categories.*;

public class CompatibilityManagerImplTest {
	private CompatibilityManagerImpl cm;
	private Transmission t;
	private Engine e;
	private PartTypeImpl pt;
	private PartTypeImpl pt1;
	private PartTypeImpl pt2;
	private PartTypeImpl pt3;
	private PartTypeImpl pt4;
	private PartTypeImpl pt5;
	

	@Before
	public void setUp() throws Exception {
		e = new Engine();
		t = new Transmission();
		pt = new PartTypeImpl(e, EH120.class);
		pt2 = new PartTypeImpl(t, TC120.class);
		pt3 = new PartTypeImpl(e, ED180.class);
		pt4 = new PartTypeImpl(t, TA5.class);
		pt5 = new PartTypeImpl(t, EG100.class);
		cm = new CompatibilityManagerImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRemovePartType() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddRequirements() {
		//good scenarios
		Collection<PartType> col = new ArrayList<>();
		Collection<PartType> colInc = new ArrayList<>();
		col.add(pt2);
		try {
			cm.addIncompatibilities(pt2, colInc);
			cm.addIncompatibilities(pt, colInc);
			cm.addRequirements(pt,col);
		} catch (ConflictingRuleException e) {
			fail("Exception : "+e);
		}
		cm = new CompatibilityManagerImpl();
		col.clear();
		col.add(pt2);
		col.add(pt3);
		try {
			cm.addRequirements(pt,col);
		} catch (ConflictingRuleException e) {
			fail("Exception : "+e);
		}
		//bad case scenario
		cm = new CompatibilityManagerImpl();
		colInc.clear();
		col.clear();
		col.add(pt2);
		col.add(pt3);
		try {
			cm.addIncompatibilities(pt2, colInc);
			cm.addIncompatibilities(pt3, colInc);
			colInc.add(pt3);
			cm.addIncompatibilities(pt, colInc);
			cm.addRequirements(pt,col);
			fail("Should have sent an exception");
		} catch (ConflictingRuleException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testAddIncompatibilities() {
		//good scenarios
		Collection<PartType> colReq = new ArrayList<>();
		Collection<PartType> colInc = new ArrayList<>();
		colInc.add(pt5);
		try {
			cm.addIncompatibilities(pt4, colInc);			
		} catch (ConflictingRuleException e) {
			fail("Exception : "+e);
		}
		cm = new CompatibilityManagerImpl();
		colInc.clear();
		colInc.add(pt2);
		colReq.add(pt3);
		try {
			cm.addRequirements(pt, colReq);
			cm.addIncompatibilities(pt,colInc);
		} catch (ConflictingRuleException e) {
			fail("Exception : "+e);
		}
		//bad case scenario
		cm = new CompatibilityManagerImpl();
		colInc.clear();
		colReq.clear();
		colInc.add(pt3);
		colReq.add(pt3);
		try {
			cm.addRequirements(pt,colReq);
			cm.addIncompatibilities(pt, colInc);
			fail("Should have sent an exception");
		} catch (ConflictingRuleException e) {
			assertTrue(true);
		}
	}

}
