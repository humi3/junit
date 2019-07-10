package unitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import mockit.Mock;
import mockit.MockUp;

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

	/**
	 * SampleクラスのFullNameメソッドについてテストする。
	 * テスト内容について
	 *・firstNameとlastName両方共データがない場合
	 * →空文字が帰る
	 *・firstNameまたはlastName片方にデータが有る場合
	 * →データが存在するデータのみ返す
	 *・firstName及びlastNameともにデータがある場合
	 * →半角スペースをはさみfirstName + " " + lastNameのデータを返す
	 */
	@Nested
	@DisplayName("full name test")
	class fullNameTest {

		@BeforeEach
		void init() {
			testSample = new UnitSample();
		}

		@Test
		@DisplayName("empty test")
		void isEmpty() {
			testSample.setFirstName("");
			testSample.setLastName("");
			assertEquals(testSample.getFullName(), "");
		}

		@Test
		@DisplayName("first name only test")
		void isFirstName() {
			testSample.setFirstName("鈴木");
			assertEquals(testSample.getFullName(), "鈴木");
		}

		@Test
		@DisplayName("last name only test")
		void isLastName() {
			testSample.setFirstName("一浪");
			assertEquals(testSample.getFullName(), "一浪");
		}

		@Test
		@DisplayName("full name test")
		void isFullName() {
			testSample.setFirstName("鈴木");
			testSample.setLastName("一浪");
			assertEquals(testSample.getFullName(), "鈴木 一浪");
		}
	}

	@Nested
	@DisplayName("BMI test")
	class BMItest {

		@BeforeEach
		void init() {
			testSample = new UnitSample();
		}

		@Test
		@DisplayName("half up up test")
		void isUp() {
			testSample.setHeight(100);
			testSample.setWeight(77.456);
			assertEquals(testSample.getBMI(), 77.46, 0.01d);
		}

		@Test
		@DisplayName("half up down test")
		void isDown() {
			testSample.setHeight(100);
			testSample.setWeight(77.454);
			assertEquals(testSample.getBMI(), 77.45, 0.01d);
		}

		@Test
		@DisplayName("arithmeticException test")
		void isArithmeticException() {
			testSample.setHeight(0);
			assertThrows(ArithmeticException.class, () -> testSample.getBMI());
		}
	}

	@Nested
	@DisplayName("degree of obesity test")
	class degreeOfObesityTest {

		@Test
		void isUnderweight() {
			new MockUp<UnitSample>() {
				@Mock
				public double getBMI() {
					return 18.4;
				}
			};

			UnitSample sample = new UnitSample();

			assertEquals(sample.getDegreeOfObesity(), "痩せ型");
		}

	}
}
