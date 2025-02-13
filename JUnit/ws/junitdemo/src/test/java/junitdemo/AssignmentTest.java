package junitdemo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AssignmentTest {
	static Assignment assignment;
	@BeforeAll
	static void createObject() {
		assignment = mock(Assignment.class);
	}
	
	@AfterAll
	static void deleteObject() {
		assignment = null;
	}
	
	@Test
	void testReturnsVoid() {
		assignment.returnsVoid();
		verify(assignment, times(1)).returnsVoid();;
	}
	
	@Test
	void exactlyThreeTimes() {
		assignment.returnsVoid();
		assignment.returnsVoid();
		assignment.returnsVoid();
		verify(assignment, times(3));
	}

}
