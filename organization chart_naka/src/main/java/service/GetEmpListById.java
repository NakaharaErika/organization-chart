package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dao.WorkDaoJDBC;

public class GetEmpListById {
    private WorkDaoJDBC dao = new WorkDaoJDBC();

    public List<HashMap<String, String>> getEmpListById(int item, String sortItem) throws Exception {
        String sortSQL = "SELECT * FROM Employee "
        		+ "INNER JOIN Department ON Employee.dep_id = Department.dep_id "
        		+ "LEFT JOIN Post ON Employee.post_id = Post.post_id "
        		+ "WHERE" + item +" ORDER BY post_id" + sortItem;
        
        //getで入ってくる時だけ常にascで並び替え
        List<Object> params = Arrays.asList(item,sortItem);
        
        List<HashMap<String, Object>> result = dao.executeQuery(sortSQL, params);
        List<HashMap<String, String>> sortedTodos = new ArrayList<>();

        for (HashMap<String, Object> row : result) {
            HashMap<String, String> todo = new HashMap<>();
            for (String key : row.keySet()) {
                todo.put(key, row.get(key).toString());
            }
            sortedTodos.add(todo);
        }
        return sortedTodos;
    }
}
