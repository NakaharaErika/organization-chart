package conroller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import entity.DBWork;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.CreateEmp;
import service.WorkService;

@WebServlet("/create")
public class CreateEmpServlet extends HttpServlet {
    
	WorkService service = new WorkService();
    CreateEmp createaccount = new CreateEmp();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	 HttpSession session = request.getSession();
         DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");

    	
    	if (loggedInUser != null) {
    		
			if (request.getAttribute("message") == null) {
				request.setAttribute("message", "社員詳細");
			}
		
		String empCode = request.getParameter("emp_code"); 
		String familyName = request.getParameter("family_name"); 
		String lastName = request.getParameter("last_name"); 
		String depId = request.getParameter("Employee.dep_id"); 
		String postId = request.getParameter("Employee.post_id"); 
		String hireDate = request.getParameter("hire_date_year") + "-" + request.getParameter("hire_date_month") + "-" + request.getParameter("hire_date_day");
		String birth = request.getParameter("birth_year") + "-" + request.getParameter("birth_month") + "-" + request.getParameter("birth_day");
		String pass = request.getParameter("pass"); 
	
		List<Object> params = Arrays.asList(empCode,familyName,lastName,depId,postId,hireDate,birth);
		
        String view = null;
        String message = null;
		try {
			//社員番号が重複していないか調べる
			 if (!service.checkempCodeExist(empCode)) {
				 createaccount.createEmp(params, pass);
			     message = "社員を登録しました";
			     view = "list";
			 } else {
				 message = "社員番号が重複しています";
				 view = "preCreate";
			 }
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		 request.setAttribute("message", message);
		 RequestDispatcher dispatcher = request.getRequestDispatcher(view);
	     dispatcher.forward(request, response);
    }
}
}
