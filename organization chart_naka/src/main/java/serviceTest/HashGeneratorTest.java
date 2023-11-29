package serviceTest;
import static org.junit.Assert.*;

import org.junit.Test;

import service.HashGenerator;


public class HashGeneratorTest {
	@Test
	public void testGenerateHash() {
		String input = "asdfg";
		String expectedHash = "f969fdbe811d8a66010d6f8973246763147a2a0914afc8087839e29b563a5af0";
	try {
		// HashGeneratorクラスを呼び出して実際のハッシュを生成
		String actualHash = HashGenerator.generateHash(input);
		// 期待されるハッシュと実際のハッシュを比較
		assertEquals(expectedHash, actualHash);
	} catch (Exception e) {
		// ハッシュ生成中にエラーが発生した場合の例外処理
		fail("Hash generation failed: " + e.getMessage());
		}
	}

	@Test
	public void testGeneraterFalse() {
		// テスト用の文字列
		String input = "abcde";
		// 期待されるハッシュ値（適切に生成されたものを用意する必要があります）
		String expectedHash = "f969fdbe811d8a66010d6f8973246763147a2a0914afc8087839e29b563a5af0";
		try {
			// HashGeneratorクラスを呼び出して実際のハッシュを生成
			String actualHash = HashGenerator.generateHash(input);
			// 期待されるハッシュと実際のハッシュを比較
			assertNotEquals(expectedHash, actualHash);
		} catch (Exception e) {
			// ハッシュ生成中にエラーが発生した場合の例外処理
			fail("Hash generation failed: " + e.getMessage());
		}
	}

	@Test
	public void testGeneraterNull() {
		// テスト用の文字列
		String input = "";
		// 期待されるハッシュ値（空文字の場合）
		String expectedHash = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
		try {
			// HashGeneratorクラスを呼び出して実際のハッシュを生成
			String actualHash = HashGenerator.generateHash(input);
			// 期待されるハッシュと実際のハッシュを比較
			assertEquals(expectedHash, actualHash);
		} catch (Exception e) {
			// ハッシュ生成中にエラーが発生した場合の例外処理
			fail("Hash generation failed: " + e.getMessage());
		}
	}
}
