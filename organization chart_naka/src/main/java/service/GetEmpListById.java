package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import dao.DBConnection;
import dao.WorkDaoJDBC;

public class GetEmpListById {
   //ログイン直後の並び順
    public List<HashMap<String, String>> getEmpListById(int item, String sortItem) throws Exception {
        String sortSQL = "SELECT * FROM Employee "
        		+ "INNER JOIN Department ON Employee.dep_id = Department.dep_id "
        		+ "LEFT JOIN Post ON Employee.post_id = Post.post_id "
        		+ "WHERE Department.dep_id = " + item + " ORDER BY Post.post_id "+ sortItem;
        
        //getで入ってくる時だけ常にascで並び替え
        List<Object> params = Arrays.asList();
        
        try (Connection conn = DBConnection.createConnection();
        	 ResultSet rs = WorkDaoJDBC.executeQuery(conn,sortSQL, params)) {
        	//ResultSetMetaDataオブジェクトで,ResultSetオブジェクトの列の型と,プロパティに関する情報を取得する
            ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
          //得られた列の数を格納
            int columnCount = metaData.getColumnCount();
       		// リストから結果を取得し、List<HashMap<String, String>>に変換
        	List<HashMap<String, String>> sortedEmps = new ArrayList<>();
       		
        	while (rs.next()) {
       			HashMap<String, String> emp = new HashMap<>();
	                for (int i = 1; i <= columnCount; i++) {	
	                	//ハッシュマップに、列名をキーにして各行ごとの値を入れていく
	                    String columnName = metaData.getColumnName(i);
	                    String value = rs.getString(i);
	                    emp.put(columnName, value);
	                }
		        sortedEmps.add(emp);
		   }
        return sortedEmps;
        }
    }
    
    
    //並び替え指定後の並び順
    public List<HashMap<String, String>> getEmpListByTag(int depId, String item, String sort) throws Exception {
        String sortSQL = "SELECT * FROM Employee "
        		+ "INNER JOIN Department ON Employee.dep_id = Department.dep_id "
        		+ "LEFT JOIN Post ON Employee.post_id = Post.post_id "
        		+ "WHERE Department.dep_id = ? ORDER BY "+ item +" "+ sort;
        
        //getで入ってくる時だけ常にascで並び替え
        List<Object> params = Arrays.asList(depId);
        
        try (Connection conn = DBConnection.createConnection();
           	 ResultSet rs = WorkDaoJDBC.executeQuery(conn,sortSQL, params)) {
           	//ResultSetMetaDataオブジェクトで,ResultSetオブジェクトの列の型と,プロパティに関する情報を取得する
               ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
             //得られた列の数を格納
               int columnCount = metaData.getColumnCount();
          		// リストから結果を取得し、List<HashMap<String, String>>に変換
           	List<HashMap<String, String>> sortedEmps = new ArrayList<>();
          		
           	while (rs.next()) {
          			HashMap<String, String> emp = new HashMap<>();
   	                for (int i = 1; i <= columnCount; i++) {	
   	                	//ハッシュマップに、列名をキーにして各行ごとの値を入れていく
   	                    String columnName = metaData.getColumnName(i);
   	                    String value = rs.getString(i);
   	                    emp.put(columnName, value);
   	                }
   		        sortedEmps.add(emp);
   		   }
           return sortedEmps;
           }
       }
    
    
    //前部署を選択した場合
    public List<HashMap<String, String>> getEmpListAll() throws Exception {
        String sortSQL = "SELECT * FROM Employee "
        		+ "INNER JOIN Department ON Employee.dep_id = Department.dep_id "
        		+ "LEFT JOIN Post ON Employee.post_id = Post.post_id";
        
        //getで入ってくる時だけ常にascで並び替え
        List<Object> params = Arrays.asList();
        
        try (Connection conn = DBConnection.createConnection();
             ResultSet rs = WorkDaoJDBC.executeQuery(conn,sortSQL, params)) {
              	//ResultSetMetaDataオブジェクトで,ResultSetオブジェクトの列の型と,プロパティに関する情報を取得する
                  ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
                //得られた列の数を格納
                  int columnCount = metaData.getColumnCount();
             		// リストから結果を取得し、List<HashMap<String, String>>に変換
              	List<HashMap<String, String>> sortedEmps = new ArrayList<>();
             		
              	while (rs.next()) {
             			HashMap<String, String> emp = new HashMap<>();
      	                for (int i = 1; i <= columnCount; i++) {	
      	                	//ハッシュマップに、列名をキーにして各行ごとの値を入れていく
      	                    String columnName = metaData.getColumnName(i);
      	                    String value = rs.getString(i);
      	                    emp.put(columnName, value);
      	                }
      		        sortedEmps.add(emp);
      		   }
              return sortedEmps;
              }
          }
}
