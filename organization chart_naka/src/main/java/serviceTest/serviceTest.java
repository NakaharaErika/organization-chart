package serviceTest;
import static org.junit.Assert.*;

import org.junit.Test;

import service.WorkService;


public class serviceTest {
	//IDとして入力された文字数の確認
	@Test
	public void countIdLength_Long() throws Exception {
		WorkService service = new WorkService();
		//5文字の社員番号を渡す
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			service.checkempCodeExist("12345");
		});
		assertEquals("IDが存在しません",exception.getMessage());
	}
	 @Test
    public void countIdLength_Short() throws Exception {
		 WorkService service = new WorkService();
        // 3文字のIDを渡して、結果がfalseであることを確認
		 RuntimeException exception = assertThrows(RuntimeException.class, () -> {
				service.checkempCodeExist("123");
			});
			assertEquals("IDが存在しません",exception.getMessage());
    }
	 @Test
    public void countIdLength_Valid() throws Exception {
		 WorkService service = new WorkService();
        // 正しい文字数
        boolean result = service.checkempCodeExist("1234");
        assertTrue(result);
    }
	 
	 //入力した文字がDB上に存在した場合、出力を返すかどうかの確認
	 @Test
    public void connectSQL_true() throws Exception {
		 WorkService service = new WorkService();
		//入力した文字が正しい場合、sqlをセットして起動
		boolean expectedResult = true;
		// メソッドの実行
        boolean actualResult = service.checkempCodeExist("A101");
        // 結果の検証
        assertEquals(expectedResult, actualResult);
    }
}
