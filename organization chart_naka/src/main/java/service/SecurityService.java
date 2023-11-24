package service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dao.WorkDaoJDBC;
import entity.DBWork;

public class SecurityService {

	WorkDaoJDBC dao = new WorkDaoJDBC();
	
	private String checkParmissionSQL = "SELECT p.permission_name FROM Permissions p "
            + "JOIN RolePermissions rp ON p.permission_id = rp.permission_id "
            + "WHERE rp.role_id = ? AND (rp.department_id IS NULL OR rp.department_id = ?)";

	
	//ユーザーの保有権限を調べる
	public Boolean hasPermission(DBWork loggedinUser,String permissionName) throws Exception{
				int postId = loggedinUser.getPostId();
				int depId = loggedinUser.getDepId();
				
				List<Object> params = Arrays.asList(postId,depId);
		        //SQLの実行
		        List<HashMap<String, Object>> result = dao.executeQuery(checkParmissionSQL,params);
		     // 結果リストをループして検証
		        for (HashMap<String, Object> row : result) {
		            Object permName = row.get("permission_name");
		            if (permName != null && permName.equals(permissionName)) {
		                return true;
		            }
		        }
		        return false;
	}

}