package service;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import dao.WorkDaoJDBC;

public class DestroyEmp{
	//アカウント削除
    private WorkDaoJDBC dao = new WorkDaoJDBC();
    private String checkUserSQL = "DELETE FROM Employee WHERE id = ?";
    
    public void destroyEmp(String id) throws Exception {
    	List<Object> params = Arrays.asList(id);
    	Connection conn = DBConnection.createConnection();
    	dao.executeUpdate(conn, checkUserSQL, params);
 
    }
}