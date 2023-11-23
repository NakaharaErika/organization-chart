package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dao.WorkDaoJDBC;

public class CreateEmp{
	//アカウント新規作成。社員番号がすでに登録されているものと重複していないかチェック後に、新規作成する
    private WorkDaoJDBC dao = new WorkDaoJDBC();
    private String checkPreSQL = "SELECT emp_code FROM Employee ORDER BY emp_code DESC limit 1";
    private String insertSQL = "INSERT INTO Employee ("
            + "emp_code, family_name, last_name, dep_id, post_id, hire_date, Birth, falsecnt, pass"
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, 0, ?);";


    public String getPreEmp() throws Exception {
    	//一番最新の社員番号を取得する
    	List<Object> params = Arrays.asList();
    	List<HashMap<String, Object>> result = dao.executeQuery(checkPreSQL, params);

        if (!result.isEmpty()) {
        	//先頭の値をString型で返す
            return (String) result.get(0).get("emp_code");
        }
		return "値が存在しません";
    }
    
    public void createEmp(List<Object> params, String pass) throws Exception {
    	//パスワードをハッシュ化して、paramsに追加
    	String hashedPassword = HashGenerator.generateHash(pass);
    	List<Object> newParams = new ArrayList<>(params);
    	newParams.add(hashedPassword);
    	dao.executeUpdate(insertSQL, newParams);
    }
}