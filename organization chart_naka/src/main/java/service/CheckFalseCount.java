package service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dao.WorkDaoJDBC;

public class CheckFalseCount{
	//IDとパスの打ち間違いをカウント。３回間違えるとログインできなくする
    private WorkDaoJDBC dao = new WorkDaoJDBC();
    private String checkfalseSQL = "SELECT falsecnt FROM Employee WHERE emp_code = ?";
    private String falsecountSQL = "UPDATE Employee SET falsecnt = falsecnt + 1 WHERE emp_code = ?";
    private String countStopSQL = "UPDATE Employee SET falsecnt = 0 WHERE emp_code = ?";
    
    //使用可能なアカウントかチェック（カウントが3以上なら不可
    public boolean isAccountLocked (String empCode) throws Exception {
	    	List<Object> params = Arrays.asList(empCode);
	    	List<HashMap<String, Object>> result = dao.executeQuery(checkfalseSQL, params);
	    	//結果値が空ではないなら、得られた値をintに変換（失敗カウントの取り出し）
	    	if(!result.isEmpty()) {
	    		int falseCount = (Integer) result.get(0).get("falsecnt");
	    		return falseCount >= 3;
	    	}
	    return false;
    }
    
    //失敗カウントを増やす
    public Boolean incrementFalseCnt(String empCode) throws Exception {
    	List<Object> params = Arrays.asList(empCode);
        dao.executeUpdate(falsecountSQL, params);
        //現在の失敗回数を取得
        List<HashMap<String, Object>> result = dao.executeQuery(checkfalseSQL, params);
        //カウントアップ後の値が3ならアカウントロック(false)
        if(!result.isEmpty()) {
        	int falseCount = (Integer) result.get(0).get("falsecnt");
        	return falseCount < 3;
        }
        return true;
    }
    
    //ユーザー認証に成功したら、失敗カウントを0にする
    public void countReset(String empCode) throws Exception {
    	List<Object> params = Arrays.asList(empCode);
    	dao.executeUpdate(countStopSQL, params);
    } 
}