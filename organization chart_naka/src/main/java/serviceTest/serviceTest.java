package serviceTest;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import entity.DBWork;
import entity.DepartmentWork;
import service.HashGenerator;
import service.WorkService;


public class serviceTest {
	//空値チェックはサーブレットでやっちゃたので、今回は無視。次回は行う
	
	//IDとして入力された文字数の確認
	@Test
    public void countIdLength_Long() throws Exception {
        WorkService service = new WorkService();
        // 5文字の社員番号を渡す
        boolean result = service.checkempCodeExist("12345");
        // 結果がfalseであることを確認
        assertFalse(result);
    }

    @Test
    public void countIdLength_Short() throws Exception {
        WorkService service = new WorkService();
        // 3文字のIDを渡して、結果がfalseであることを確認
        boolean result = service.checkempCodeExist("123");
        assertFalse(result);
    }

	 //入力した文字がDB上に存在しない場合、出力を返すかどうかの確認
	 @Test
    public void connectSQLforId_false() throws Exception {
		 WorkService service = new WorkService();
		//入力した文字が正しいと仮定して、sqlをセットして起動
		boolean expectedResult = false;
		// メソッドの実行
        boolean actualResult = service.checkempCodeExist("1111");
        // 結果の検証
        assertEquals(expectedResult, actualResult);
    }
	 
	//入力した文字がDB上に存在した場合、出力を返すかどうかの確認
	 @Test
    public void connectSQLforId_true() throws Exception {
		 WorkService service = new WorkService();
		//入力した文字が正しいと仮定して、sqlをセットして起動
		boolean expectedResult = true;
		// メソッドの実行
        boolean actualResult = service.checkempCodeExist("A001");
        // 結果の検証
        assertEquals(expectedResult, actualResult);
    }
	 
	//入力したパスワードがDB上に存在した場合、出力を返すかどうかの確認
	 @Test
	public void connectSQLforPass_true() throws Exception{
		WorkService service = new WorkService();
		//idとパスワードをセット
		String pass = "Sato";
		DBWork actualResult = service.login("A001",pass);
        //得られた結果が一致しているか確認
		assertEquals("A001", actualResult.getEmpCode());
	}
	
	//入力したパスワードがDB上に存在しない場合、出力を返すかどうかの確認
	 @Test
	public void connectSQLforPass_false() throws Exception{
		WorkService service = new WorkService();
		//idとパスワードをセット
		String pass = "Kato";
		String hashedPassword = HashGenerator.generateHash(pass);
		DBWork actualResult = service.login("A001",hashedPassword);
		// actualResultがnullでないことを確認
        assertNull(actualResult);
	}
	 
	 //部署リストをDBに正常に取得できている
	 @Test
	 public void getAllDepartments_true() throws Exception {
		 WorkService service = new WorkService();
		 List<DepartmentWork> actualResult = service.getAllDepartments();
		 String depName = (String) actualResult.get(0).getDepName();
		 assertEquals("総務部",depName);
	 }
	 
}
