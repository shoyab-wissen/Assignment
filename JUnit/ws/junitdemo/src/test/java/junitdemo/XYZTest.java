package junitdemo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class XYZTest {
	
	@Test
	void testDemo() {
		XYZ xyz = mock(XYZ.class);
		when(xyz.demo()).thenReturn(true);
		assertTrue(xyz.demo());
	}
}
