package service;

import java.util.Arrays;
import java.util.List;

import dao.WorkDaoJDBC;

public class DestroyEmp{
	//アカウント削除
    private WorkDaoJDBC dao = new WorkDaoJDBC();
    private String checkUserSQL = "DELETE FROM todo WHERE No = ?\"";
    
    public void destroyEmp(String id) throws Exception {
    	List<Object> params = Arrays.asList(id);
    	dao.executeUpdate(checkUserSQL, params);
 
    }
}