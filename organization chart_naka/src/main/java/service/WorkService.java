package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
	        List<HashMap<String, Object>> result = dao.executeQuery(checkUserIdSQL,params);
	        //もし、得られた結果がnulはなく、１行目のキーの中身がnullでなければ（この場合は1)なら、1(true)を返す
		        //"COUNT(*))"は、sql実行の時に結果として得られる列の一つ
				if(!result.isEmpty() && result.get(0).get("COUNT(*)") !=null){
					return (Long) result.get(0).get("COUNT(*)") > 0;
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
		List<HashMap<String, Object>> result = dao.executeQuery(checkUserPassSQL, params);
		//結果をDTOに挿入
	    if(!result.isEmpty()) {
	    	HashMap<String,Object> row = result.get(0);
	    	//DTO作成
	    	DBWork dbWork = new DBWork(id);
	    	dbWork.setEmpCode((String) row.get("emp_code"));
	    	dbWork.setFamilyName((String) row.get("family_name"));
	    	dbWork.setLastName((String) row.get("last_name"));
	    	dbWork.setDepId((Integer) row.get("dep_id"));
	    	dbWork.setPostId((Integer) row.get("post_id"));
	    	dbWork.setDepName((String) row.get("dep_name"));
	    	dbWork.setPostName((String) row.get("post_name"));
	    	return dbWork;
	    }
	    return null;
	}
    
    //部署リストをDTOに登録
    public List<DepartmentWork> getAllDepartments() throws Exception {
    	//変数を格納
    	List<Object> params = Arrays.asList();
        List<HashMap<String, Object>> result = dao.executeQuery(getAllDepartmentsSQL, params);
        List<DepartmentWork> departments = new ArrayList<>();
        //得られた部署IDと部署名の組み合わせを登録
        for (HashMap<String, Object> row : result) {
            DepartmentWork department = new DepartmentWork();
            department.setDepId((int) row.get("dep_id"));
            department.setDepName((String) row.get("dep_name"));
            departments.add(department);
        }
        return departments;
    }
    
    //役職リストをDTOに登録
    public List<PostWork> getAllPosts() throws Exception {
    	//変数を格納
    	List<Object> params = Arrays.asList();
    	List<HashMap<String, Object>> result = dao.executeQuery(getAllPostsSQL, params);
    	List<PostWork> posts = new ArrayList<>();
    	//得られた部署IDと部署名の組み合わせを登録
    	for (HashMap<String, Object> row : result) {
    		PostWork post = new PostWork();
    		post.setPostId((int) row.get("post_id"));
    		post.setPostName((String) row.get("post_name"));
    		posts.add(post);
    	}
    	return posts;
    }

}