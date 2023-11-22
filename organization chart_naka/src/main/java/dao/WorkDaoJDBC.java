package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorkDaoJDBC {
	//DB接続のための共有メソッド
	//今回はこれでOKだが、実務上ではConnectionはDaoの外で行う
	private Connection createConnection() throws ClassNotFoundException, SQLException {
		String dbUrl = "jdbc:mysql://localhost/prac_1";
		String dbUser = "root";
		String dbPassword = "";
		// JDBCドライバーをロード
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
	}
	
	//汎用メソッド(削除、更新、作成)
	public void executeUpdate(String sql, List<Object> params) throws ClassNotFoundException, SQLException {
		try(Connection conn = createConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)){
			setParameters(stmt, params);
			stmt.executeUpdate();
		}
	}
	
	//汎用メソッド（読み出し）
	//今回はResultsetを返すパターンで練習
	public List<HashMap<String,Object>> executeQuery(String sql, List<Object> params) throws SQLException, ClassNotFoundException{
		try(Connection conn = createConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)){
			setParameters(stmt, params);
			try (ResultSet rs = stmt.executeQuery()) {
                return resultSetToList(rs);
            }
		}
	}
	
	//変数の格納
	private void setParameters(PreparedStatement stmt, List<Object> params) throws SQLException{
		for (int i = 0; i < params.size(); i++) {
			stmt.setObject(i + 1, params.get(i));
		}
	}
	
	// ResultSetから中身を取り出し、キーバリュー形式のリストに変換
    private List<HashMap<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
        List<HashMap<String, Object>> list = new ArrayList<>();
        //ResultSetMetaDataオブジェクトでResultSetオブジェクトの列の型とプロパティに関する情報を取得する
        ResultSetMetaData md = rs.getMetaData();
        //selectで得られた列数を確認
        int columns = md.getColumnCount();
        //得られた行数分回す
        while (rs.next()) {
            HashMap<String, Object> row = new HashMap<>(columns);
            //得られた列数分回して、値を入れる
            for (int i = 1; i <= columns; ++i) {
            	//DB上の列名をキーにして、対応する値を入れる
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            //１列分のデータを確定してリストに挿入
            list.add(row);
        }
        return list;
    }
}