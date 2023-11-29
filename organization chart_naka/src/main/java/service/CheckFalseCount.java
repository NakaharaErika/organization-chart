package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Arrays;
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
	    	try (Connection conn = DBConnection.createConnection();
		         ResultSet rs = dao.executeQuery(conn, checkfalseSQL, params)) {
	    	//結果値が空ではないなら、得られた値をintに変換（失敗カウントの取り出し）
	    		if (rs.next()) {
	                int falseCount = rs.getInt("falsecnt");
	                return falseCount >= 3;
	            }
	    	}
	    return false;
    }
    
    //失敗カウントを増やす
    public Boolean incrementFalseCnt(String empCode) throws Exception {
    	List<Object> params = Arrays.asList(empCode);
    	try (Connection conn = DBConnection.createConnection();
		     ResultSet rs = dao.executeQuery(conn, falsecountSQL, params)) {
	        //カウントアップ後の値が3ならアカウントロック(false)
	        if(rs.next()) {
	        	int falseCount = rs.getInt("falsecnt");
	        	return falseCount < 3;
	        }
    	}
        return true;
    }
    
    //ユーザー認証に成功したら、失敗カウントを0にする
    public void countReset(String empCode) throws Exception {
    	List<Object> params = Arrays.asList(empCode);
    try(Connection conn = DBConnection.createConnection()){
    	dao.executeUpdate(conn, countStopSQL, params);
    }
    } 
}