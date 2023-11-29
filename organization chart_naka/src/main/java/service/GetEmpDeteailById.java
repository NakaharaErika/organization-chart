package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

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
    	try (Connection conn = DBConnection.createConnection();
      		 ResultSet rs = dao.executeQuery(conn,empListSQL, params)) {
    		// リストから最初の結果を取得し、HashMap<String, String>に変換
    		if (rs.next()) {
                HashMap<String, String> empDetails = new HashMap<>();
               //ResultSetMetaDataオブジェクトで,ResultSetオブジェクトの列の型と,プロパティに関する情報を取得する
                ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
                //得られた列の数を格納
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                	//ハッシュマップに、列名をキーにして各行ごとの値を入れていく
                    String columnName = metaData.getColumnName(i);
                    String value = rs.getString(i);
                    empDetails.put(columnName, value);
                }
                return empDetails;
            }
    	}
        return null;
    }
}