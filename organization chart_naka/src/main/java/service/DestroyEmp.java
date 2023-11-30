package service;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import dao.DBConnection;
import dao.WorkDaoJDBC;

public class DestroyEmp{
	//アカウント削除
    private String checkUserSQL = "DELETE FROM Employee WHERE id = ?";
    
    public void destroyEmp(String id) throws Exception {
    	List<Object> params = Arrays.asList(id);
    	Connection conn = DBConnection.createConnection();
    	WorkDaoJDBC.executeUpdate(conn, checkUserSQL, params);
 
    }
}