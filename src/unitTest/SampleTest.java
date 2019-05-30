package unitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * ユニットテストの練習
 *
 */
@DisplayName("Sampleのテストケース")
class SampleTest {

	@Test
	@DisplayName("isEven test")
	void isEvenTest() {
		assertEquals(Sample.isEven(2), true);
		assertEquals(Sample.isEven(3), false);
	}

}
