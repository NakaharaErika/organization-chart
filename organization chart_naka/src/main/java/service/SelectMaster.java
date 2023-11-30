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

public class SelectMaster {
    //マスタ用テーブルを作成する
    public List<HashMap<String, String>> selectMster(String mst) throws Exception {
    	List<Object> params = Arrays.asList();
        String mstSQL = "SELECT * FROM " + mst;
        
        try (Connection conn = DBConnection.createConnection();
             ResultSet rs = WorkDaoJDBC.executeQuery(conn,mstSQL, params)) {
         	//ResultSetMetaDataオブジェクトで,ResultSetオブジェクトの列の型と,プロパティに関する情報を取得する
             ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
           //得られた列の数を格納
             int columnCount = metaData.getColumnCount();
        		// リストから結果を取得し、List<HashMap<String, String>>に変換
             List<HashMap<String, String>> masters = new ArrayList<>();

             while (rs.next()) {
      			HashMap<String, String> mster = new HashMap<>();
	                for (int i = 1; i <= columnCount; i++) {	
	                	//ハッシュマップに、列名をキーにして各行ごとの値を入れていく
	                    String columnName = metaData.getColumnName(i);
	                    String value = rs.getString(i);
	                    mster.put(columnName, value);
	                }
	                masters.add(mster);
		     }
		   return masters;
		}
    }
}
