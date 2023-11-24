package service;

import java.util.Arrays;
import java.util.List;

import dao.WorkDaoJDBC;

public class ResetEmp{
	//アカウントと紐づいているパスワードを新しいものに変更
	private WorkDaoJDBC dao = new WorkDaoJDBC();
    private String ResetPassSQL = "UPDATE Emproyee SET pass = ? WHERE emp_code = ?";

    public void ResetPass(String userId, String password) throws Exception {
    	//パスワードをハッシュ化
    	String hashedPassword = HashGenerator.generateHash(password);
    	List<Object> params = Arrays.asList(userId, hashedPassword);
		dao.executeQuery(ResetPassSQL, params);   
    }
}