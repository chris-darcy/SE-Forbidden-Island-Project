package UnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import WaterLevel.WaterLevel;

public class WaterLevelTest {
	WaterLevel waterlevel;
	WaterLevel validation;
	
	@Before
	public void setUp() {
		waterlevel = WaterLevel.getInstance();
		validation = WaterLevel.getInstance();
	}

	@Test
	public void test_single_instance() {
		int level = 5;
		assertEquals("The singleton waterlevel starts at 0",0,waterlevel.getCurrentWaterLevel());
		assertEquals("The singleton waterlevel starts at 0",0,validation.getCurrentWaterLevel());
		
		waterlevel.setWaterLevel(level);
		assertEquals("waterlevel should change to 5 across instances",level,waterlevel.getCurrentWaterLevel());
		assertEquals("waterlevel should change to 5 across instances",level,validation.getCurrentWaterLevel());
	}
	
	@After
	public void tearDown() {
		waterlevel = null;
	}
}
