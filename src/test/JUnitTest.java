package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * JUnitの基本的な書き方についてまとめたクラス
 * JUnit5では、テストクラスはpublicにする必要は無い。
 */
@DisplayName("JUnit基本的な書き方")
class JUnitTest {

	/*
	 * 全テストの実行前に呼ばれる処理には、@BeforeAllアノテーションを付ける。
	 */
	@BeforeAll
	static void initAll() {
		System.out.println("テスト実行前に1回実行されます。");
	}

	/*
	 * 各テストごとに、テスト実行時前に呼ばれる処理には、@BeforeEachをつける
	 */
	@BeforeEach
	void init() {
		System.out.println("各テスト前に実行されます。");
	}

	/*
	 * fail()
	 * テストを失敗させる
	 */
	@Test
	@DisplayName("failを使ってテストを失敗させる")
	void failTest() {
		fail("絶対に失敗する。");
	}

	/*
	 * fail()
	 * テストを失敗させる
	 */
	@Test
	@DisplayName("failを使ってテストを失敗させる2")
	void failTest2() {
		try {
			throw new Exception("test");
		} catch (Exception e) {
			fail("失敗", e);
		}
	}

	/*
	 * assertNull()
	 * Nullを判定する
	 *
	 * assertNotNull()
	 * Nullじゃないことを判定する。
	 */
	@Test
	@DisplayName("Nullの判定")
	void _null() {
		assertNull(null);
		assertNotNull("Nullじゃない");
	}

	/*
	 * assertEquals()
	 * 同じ値か比較する。
	 *
	 * assertNotEquals()
	 * 違う値か比較する。
	 */
	@Test
	@DisplayName("等値の判定")
	void equals() {
		//String や int
		assertEquals("XYZ", "xyz".toUpperCase());
		assertNotEquals("XYZ", "XYZ".toLowerCase());
		assertEquals(10, 5 + 5);
		assertNotEquals(9, 10);
		// 浮動小数点数
		// 浮動小数点数は完全一致になるとは限らないので第三引数で一致する範囲(delta)を指定する
		// float
		assertEquals(0.333f, 1 / 3f, 0.001f);
		// double
		assertEquals(0.333d, 1 / 3d, 0.001d);
	}

	/*
	 * assertArrayEquals
	 * 配列を比較する。（配列の配列も、再帰的に比較される）
	 */
	@Test
	@DisplayName("配列の判定")
	void array() {
		int[] p = { 3, 4, 5 };
		int[] g = { 3, 4, 5 };
		assertArrayEquals(p, g);
	}

	/*
	 * assertIterableEquals
	 * リスト(Iterable)の比較
	 * assertArrayEqualsでも比較できるけが、不一致だった要素を表示してくれる。
	 */
	@Test
	@DisplayName("リストの判定")
	void iterable() {
		List<String> expected = Arrays.asList("a", "b", "c");
		List<String> actual = Arrays.asList("a", "b", "c");
		assertIterableEquals(expected, actual);
	}

	@Test
	@DisplayName("リストの判定 エラー表示の違いを確認する")
	void iterable2() {
		List<String> expected = Arrays.asList("a", "b", "c");
		List<String> actual = Arrays.asList("a", "b", "c", "d");
		assertAll("isError",
				() -> assertEquals(expected, actual, "assertEquals"),
				() -> assertIterableEquals(expected, actual, "assertIterableEquals"));
	}

	/*
	 * assertSame
	 * インスタンスが同じか判定する。
	 *
	 * assertNotSame
	 * インスタンスが違うか判定する。
	 */
	@Test
	@DisplayName("インスタンスの判定")
	void same() {
		String s = new String("xyz");
		assertEquals("xyz", s);
		assertNotSame("xyz", s);
		assertSame("xyz", s.intern());
	}

	/*
	 * assertAll
	 * assertAllを使用すると、1つのテストに失敗しても残りのテストがすべて実行される。
	 * assertEquals等は、テストが失敗した時点でエラーが発生するのでそれ以降のテストは実行されない。
	 */
	@Test
	@DisplayName("assertAllを使用してみる")
	void all() {
		String hogehoge = "hogehoge";
		//以下2つのテストが実行される。2つともエラーだが2つ実行される。
		assertAll("assertAll test",
				() -> assertEquals("HogeHoge", hogehoge, "HogeHoge"),
				() -> assertEquals("higehige", hogehoge, "highige"));
	}

	/*
	 * assertThrows
	 *  第一引数：発生するであろう例外
	 *  第二引数：ラムダ式でテスト対象の処理を実行
	 */
	@Test
	@DisplayName("例外の判定")
	void exception1() {
		Example1 target = new Example1();
		assertThrows(IllegalArgumentException.class, () -> target.execute(null));
	}

	/*
	 * assertTimeout
	 * assertTimeoutPreemptively
	 * タイムアウトしないことを確認する(一定時間内に終わる)
	 *
	 * assertTimeout と assertTimeoutPreemptivelyの違い
	 * assertTimeout
	 *  テスト対象の処理が終わるまで待ち、終わってからAssertionFailedErrorを発生させる。
	 * assertTimeoutPreemptively
	 *  タイムアウト時間になったらすぐに（テスト対象の処理が終わるのを待たずに）AssertionFailedErrorを発生させる。
	 */
	@Test
	@DisplayName("タイムアウト'しない'ことの判定")
	void timeout() {
		assertTimeout(Duration.ofSeconds(2), () -> Thread.sleep(1000));
		assertTimeoutPreemptively(Duration.ofSeconds(2), () -> Thread.sleep(1000));
	}

	/*
	 * @Disabledをつけるとそのメソッドのテストは行われない
	 */
	@Test
	@Disabled
	@DisplayName("行われないテスト")
	void test() {
		fail("行われないよ");
	}

	/*
	 * @Nested
	 * 内部クラスに@Nestedをつけるとそのクラスもテスト対象となる。
	 * 内部クラスのテストがグルーピングされる
	 */
	@Nested
	class Nest {
		@Test
		@DisplayName("@Nestedを使ったテスト")
		void array() {
			int[] p = { 3, 4, 5 };
			int[] g = { 3, 4, 5 };
			assertArrayEquals(p, g);
		}

		@Test
		@DisplayName("@Nestedを使ったテスト2")
		void array2() {
			int[] p = { 3, 4, 5 };
			int[] g = { 2, 4, 5 };
			assertArrayEquals(p, g);
		}
	}

	/*
	 * 各テストごとに、テスト実行時後に呼ばれる処理には、@AfterEachをつける
	 */
	@AfterEach
	void tearDown() {
		System.out.println("各テスト後に実行されます。");
	}

	/*
	 * 全テストの実行後に呼ばれる処理には、@AfterAllアノテーションを付ける。
	 */
	@AfterAll
	static void tearDownAll() {
		System.out.println("テスト後に1回実行されます。");
	}
}

/**
 * 例外の判定の際に使用する。
 */
class Example1 {
	public void execute(String arg) {
		if (arg == null) {
			throw new IllegalArgumentException();
		}
	}
}