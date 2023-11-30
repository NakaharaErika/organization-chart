package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import dao.DBConnection;
import dao.WorkDaoJDBC;
import entity.DBWork;

public class SecurityService {
	private String checkParmissionSQL = "SELECT p.permission_name FROM Permissions p "
            + "JOIN RolePermissions rp ON p.permission_id = rp.permission_id "
            + "WHERE rp.role_id = ? AND (rp.department_id IS NULL OR rp.department_id = ?)";

	
	//ユーザーの保有権限を調べる
	public Boolean hasPermission(DBWork loggedinUser,String permissionName) throws Exception{
		int postId = loggedinUser.getPostId();
		int depId = loggedinUser.getDepId();
		
		List<Object> params = Arrays.asList(postId,depId);
        //SQLの実行
		try (Connection conn = DBConnection.createConnection();
	         ResultSet rs = WorkDaoJDBC.executeQuery(conn, checkParmissionSQL, params)) {
			// 結果セットをループして検証
	        while (rs.next()) {
	            String permName = rs.getString("permission_name");
	            if (permName != null && permName.equals(permissionName)) {
	                return true;
	            }
	        }
	    }
	    return false;
	}
}