package junitdemo;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

@DisplayName("Arithmetic Operations")
@TestInstance(Lifecycle.PER_CLASS)
class CalculatorTest {

	static Calculator c1;
	static int x = 0;
	static boolean condition = false;
	@BeforeAll
	static void createCalculator() {
		c1 = new Calculator();
		if(x != 0) {
			condition = true;
		}
		System.out.println("-----------Started------------");
	}
	
	@AfterAll
	static void removeCalculator() {
		c1 = null;
		System.out.println("-----------Finished------------");
	}
	
	@BeforeEach
	void abc() {
		System.out.println("Before each test case ............");
	}
	
	@AfterEach
	void xyz() {
		System.out.println("After each test case ............");
	}
	
	
	@Test
	@Disabled
	void test() {
		assertTrue(true);
	}
	
	@Test
	@DisplayName("Add method")
	@Tag("Math")
	void testAdd() {
		assertAll(
			()->assertEquals(20, c1.add(10, 10), ()->"Sum result is not right"),
			()->assertEquals(30, c1.add(10, 20), ()->"Sum result is not right"),
			()->assertEquals(40, c1.add(10, 20), ()->"Sum result is not right"),
			()->assertEquals(15, c1.add(5, 5), ()->"Sum result is not right"),
			()->assertEquals(15, c1.add(10, 5), ()->"Sum result is not right"),
			()->assertEquals(15, c1.add(10, 5), ()->"Sum result is not right"),
			()->assertEquals(15, c1.add(10, 5), ()->"Sum result is not right"),
			()->assertEquals(15, c1.add(10, 5), ()->"Sum result is not right")
		);
	}
	@Test
	@Tag("Math")
	void testSub() {
		assertEquals(-10, c1.sub(10, 20), ()->"Sub result is not right");
	}
	@Test
	@Tag("Math")
	void testMul() {
		assertEquals(200, c1.mul(10, 20), ()->"Mul result is not right");
	}
	@Test
	@Tag("Math")
	void testDiv() {
		assertEquals(1, c1.div(10, 10), ()->"Div result is not right");
		assertThrows(ArithmeticException.class, ()-> c1.div(10, 0));
	}
	@Test
	@DisplayName("Modulo method")
//	@Disabled
	@Tag("Math")
	void testMod() {
		assertAll(
			()->assertEquals(0, c1.mod(10, 10), ()->"Sum result is not right"),
			()->assertEquals(10, c1.mod(10, 20), ()->"Sum result is not right"),
			()->assertEquals(10, c1.mod(10, 20), ()->"Sum result is not right"),
			()->assertEquals(0, c1.mod(5, 5), ()->"Sum result is not right"),
			()->assumeTrue(condition),
			()->assertEquals(0, c1.mod(10, 5), ()->"Sum result is not right"),
			()->assertEquals(0, c1.mod(10, 5), ()->"Sum result is not right"),
			()->assertEquals(0, c1.mod(10, 5), ()->"Sum result is not right"),
			()->assertEquals(0, c1.mod(10, 5), ()->"Sum result is not right")
		);
	}
	
	@Test
//	@EnabledOnJre(value = JRE.JAVA_17)
	@EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_25)
	@Tag("Demo")
	@Tag("Math")
	void testForLambdaExpression() {
		System.out.println("Testing for lambda expression");
	}
	
	@Test
	@EnabledOnOs(OS.WINDOWS)
	@Tags(value = {@Tag("OS"), @Tag("Windows")})
	void testDll() {
		System.out.println("Testing for DLL Files");
	}
	
	@Test
	@EnabledOnOs(value = {OS.LINUX, OS.MAC})
	@Tag("OS")
	void testShellScript() {
		System.out.println("Testing for Shell Scripts");
	}
	
	@Test
	@Tag("Dynamic")
	void testDynamically() {
		System.out.println("Test Dynamically started");
		assertEquals(0, c1.mod(10, 5), ()->"Sum result is not right");
		assertEquals(0, c1.mod(10, 5), ()->"Sum result is not right");
		assumeTrue(condition);
		assertEquals(0, c1.mod(10, x), ()->"Sum result is not right");
		assertEquals(0, c1.mod(5, x), ()->"Sum result is not right");
		System.out.println("Test Dynamically Finished");
	}
}
