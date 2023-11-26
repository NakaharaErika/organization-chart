package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dao.WorkDaoJDBC;

public class SelectMaster {
    private WorkDaoJDBC dao = new WorkDaoJDBC();
    //ログイン直後の並び順
    public List<HashMap<String, String>> selectMster(String mst) throws Exception {
    	List<Object> params = Arrays.asList();
        String mstSQL = "SELECT * FROM " + mst;
        
        List<HashMap<String, Object>> result = dao.executeQuery(mstSQL, params);
        List<HashMap<String, String>> master = new ArrayList<>();

        for (HashMap<String, Object> row : result) {
            HashMap<String, String> todo = new HashMap<>();
            for (String key : row.keySet()) {
                todo.put(key, row.get(key).toString());
            }
            master.add(todo);
        }
        return master;
    }
}
