package service;

import java.sql.Connection;
import java.util.List;

import dao.WorkDaoJDBC;

public class UpdateEmp {
    private WorkDaoJDBC dao = new WorkDaoJDBC();
    //ログイン直後の並び順
    public void updateEmp(List<Object> params) throws Exception {
        String sortSQL = "UPDATE Employee SET "
                + "emp_code = ?, "
                + "family_name = ?, "
                + "last_name = ?, "
                + "dep_id = ?, "
                + "post_id = ?, "
                + "hire_date = ?, "
                + "Birth = ? "
                + "WHERE id = ?;";
        
        try(Connection conn = DBConnection.createConnection()){
        	dao.executeUpdate(conn, sortSQL, params);
        }
    }
}
