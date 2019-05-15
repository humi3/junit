package junitTest.test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import junitTest.main.Sample;

class SampleTest {

	@Test
	void testNum() {
		assertEquals(10, Sample.num());
		assertEquals("10じゃない", 10, Sample.num());
	}

}
