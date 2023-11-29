package service;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import dao.WorkDaoJDBC;

public class ResetEmp{
	//アカウントと紐づいているパスワードを新しいものに変更
	private WorkDaoJDBC dao = new WorkDaoJDBC();
    private String ResetPassSQL = "UPDATE Employee SET pass = ? WHERE emp_code = ?";

    public void ResetPass(String userId, String password) throws Exception {
    	//パスワードをハッシュ化
    	String hashedPassword = HashGenerator.generateHash(password);
    	List<Object> params = Arrays.asList(hashedPassword, userId);
    	
    	try(Connection conn = DBConnection.createConnection()){
    	dao.executeUpdate(conn, ResetPassSQL, params); 
    	}
    }
}