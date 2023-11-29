package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dao.WorkDaoJDBC;
import entity.DBWork;
import entity.DepartmentWork;
import entity.PostWork;

public class WorkService {
	 
	WorkDaoJDBC dao = new WorkDaoJDBC();
	
	private String checkUserIdSQL = "SELECT COUNT(*) FROM Employee WHERE emp_code = ?";
	private String checkUserPassSQL = "SELECT * FROM Employee "
									+ "INNER JOIN Department ON Employee.dep_id = Department.dep_id "
									+ "LEFT JOIN Post ON Employee.post_id = Post.post_id "
									+ "WHERE emp_code=? AND pass=?";
	private String getAllDepartmentsSQL = "SELECT * FROM Department";
	private String getAllPostsSQL = "SELECT * FROM Post";
	
	//ユーザーIDが4桁で、既に登録されているか調べる
	public Boolean checkempCodeExist(String userId) throws Exception{
		//IDが4桁か確認
		if (userId.length() == 4) {
			//asList配列：引数で指定した配列をリストとして返す（?部分に入れる変数を格納）
			List<Object> params = Arrays.asList(userId);
	        //SQLの実行
			try (Connection conn = DBConnection.createConnection();
	             ResultSet rs = dao.executeQuery(conn, checkUserIdSQL, params)) {
	                return rs.next() && rs.getLong(1) > 0;
	            }
		}
		return false;
    }
	
	//パスワードが一致しているか調べる
    public DBWork login(String id,String pass) throws Exception {
		String hashedPassword = HashGenerator.generateHash(pass);
		//変数を格納
		List<Object> params = Arrays.asList(id, hashedPassword);
		//SQL実行
		try (Connection conn = DBConnection.createConnection();
	         ResultSet rs = dao.executeQuery(conn, checkUserPassSQL, params)) {
		//結果をDTOに挿入
		    if(rs.next()) {
		    	//DTO作成
		    	DBWork dbWork = new DBWork(id);
		    	dbWork.setEmpCode(rs.getString("emp_code"));
	            dbWork.setFamilyName(rs.getString("family_name"));
	            dbWork.setLastName(rs.getString("last_name"));
	            dbWork.setDepId(rs.getInt("dep_id"));
	            dbWork.setPostId(rs.getInt("post_id"));
	            dbWork.setDepName(rs.getString("dep_name"));
	            dbWork.setPostName(rs.getString("post_name"));
		    	return dbWork;
		    }
		}
	    return null;
	}
    
    //部署リストをDTOに登録
    public List<DepartmentWork> getAllDepartments() throws Exception {
    	List<DepartmentWork> departments = new ArrayList<>();
    	//変数を格納
    	List<Object> params = Arrays.asList();
    	try (Connection conn = DBConnection.createConnection();
   	         ResultSet rs = dao.executeQuery(conn, getAllDepartmentsSQL, params)) {
    		while (rs.next()) {
                DepartmentWork department = new DepartmentWork();
                department.setDepId(rs.getInt("dep_id"));
                department.setDepName(rs.getString("dep_name"));
                departments.add(department);
            }
    	}
        return departments;
    }
    
    //役職リストをDTOに登録
    public List<PostWork> getAllPosts() throws Exception {
    	List<PostWork> posts = new ArrayList<>();
    	//変数を格納
    	List<Object> params = Arrays.asList();
    	try (Connection conn = DBConnection.createConnection();
      	     ResultSet rs = dao.executeQuery(conn, getAllPostsSQL, params)) {
    	//得られた部署IDと部署名の組み合わせを登録
    		while (rs.next()) {
	    		PostWork post = new PostWork();
	    		post.setPostId(rs.getInt("post_id"));
	    		post.setPostName(rs.getString("post_name"));
	    		posts.add(post);
    		}
    	}
    	return posts;
    }

}