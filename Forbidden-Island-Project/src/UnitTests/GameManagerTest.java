package UnitTests;

import java.util.Scanner;

import org.junit.*;

import java.io.ByteArrayInputStream;
import Game.GUI;
import java.io.InputStream;

public class GameManagerTest {
	InputStream in;
	GUI gui;
	String input;

	
	@Before
	public void setUp() {
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GUI.getInstance().setScanner(new Scanner(in));
		
	}
	
	@After
	public void tearDown() {
		
	}
		
		
}
