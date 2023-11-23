package service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dao.WorkDaoJDBC;

public class GetEmpDeteailById {
    private WorkDaoJDBC dao = new WorkDaoJDBC();
    private String empListSQL = "SELECT * FROM Employee "
    		+ "INNER JOIN Department ON Employee.dep_id = Department.dep_id "
    		+ "LEFT JOIN Post ON Employee.post_id = Post.post_id "
    		+ "WHERE Employee.id = ?";

    
    public HashMap<String, String> getEmpDeteailById(String no) throws Exception {
    	List<Object> params = Arrays.asList(no);
    	//かくれ社員Noに紐づく詳細情報を取り出す。
        List<HashMap<String, Object>> result = dao.executeQuery(empListSQL, params);
        
        // リストから最初の結果を取得し、HashMap<String, String>に変換
        if (!result.isEmpty()) {
            HashMap<String, Object> row = result.get(0);
            HashMap<String, String> empDetails = new HashMap<>();
            for (String key : row.keySet()) {
                Object value = row.get(key);
                empDetails.put(key, value != null ? value.toString() : null);
            }
            return empDetails;
        }
        return null;
    }
}