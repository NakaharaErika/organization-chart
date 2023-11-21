package service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dao.WorkDaoJDBC;
import entity.DBWork;

public class DBWorkService {

	WorkDaoJDBC dao = new WorkDaoJDBC();
	
	private String checkUserIdSQL = "SELECT COUNT(*) FROM Employee WHERE emp_code = ?";
	private String checkUserPassSQL = "SELECT * FROM Employee WHERE emp_code=? AND pass=?";
	
	//ユーザーIDが既に登録されているか調べる
	public Boolean checkempCodeExist(String userId) throws Exception{
		//asList配列：引数で指定した配列をリストとして返す（?部分に入れる変数を格納）
		List<Object> params = Arrays.asList(userId);
        //SQLの実行
        List<HashMap<String, Object>> result = dao.executeQuery(checkUserIdSQL,params);
        //もし、得られた結果がnulはなく、１行目のキーの中身がnullでなければ（この場合は1)なら、1(true)を返す
        //"COUNT(*))"は、sql実行の時に結果として得られる列の一つ
		if(!result.isEmpty() && result.get(0).get("COUNT(*))") !=null){
			return (Long) result.get(0).get("COUNT(*)") > 0;
		}
		return false;
    }
	
	//パスワードが一致しているか調べる
    public DBWork login(String id,String pass) throws Exception {
		String hashedPassword = HashGenerator.generateHash(pass);
		//変数を格納
		List<Object> params = Arrays.asList(id, hashedPassword);
		//SQL実行
		List<HashMap<String, Object>> result = dao.executeQuery(checkUserPassSQL, params);
		//結果をDTOに挿入
	    if(!result.isEmpty()) {
	    	HashMap<String,Object> row = result.get(0);
	    	//DTO作成
	    	DBWork dbWork = new DBWork(id);
	    	dbWork.setFamilyName((String) row.get("family_name"));
	    	dbWork.setLastName((String) row.get("last_name"));
	    	return dbWork;
	    }
	    return null;
	}
}