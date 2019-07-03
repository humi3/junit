package unitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * ユニットテストの練習
 *
 */
@DisplayName("Sampleのテストケース")
class UnitSampleTest {

	private UnitSample testSample;

	@BeforeEach
	void init() {
		testSample = new UnitSample();
	}

	@Nested
	@DisplayName("full name test")
	class fullNameTest {

		@BeforeEach
		void init() {
			testSample = new UnitSample();
		}

		@Test
		@DisplayName("empty test")
		void isEmpty(){
			testSample.setFirstName("");
			testSample.setLastName("");
			assertEquals(testSample.getFullName(),"");
		}

		@Test
		@DisplayName("full name test")
		void isFirstName() {
			testSample.setFirstName("鈴木");
			testSample.setLastName("一浪");
			assertAll(
					() -> assertEquals("",testSample.getFullName(),"鈴木") ,
					() -> assertEquals("",testSample.getFullName(),"鈴木") ,
					() -> assertEquals("",testSample.getFullName(),"鈴木")
					);
		}
	}
}
