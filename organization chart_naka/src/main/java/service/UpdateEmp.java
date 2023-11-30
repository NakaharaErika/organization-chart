package service;

import java.sql.Connection;
import java.util.List;

import dao.DBConnection;
import dao.WorkDaoJDBC;

public class UpdateEmp {
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
        	WorkDaoJDBC.executeUpdate(conn, sortSQL, params);
        }
    }
}
