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
import service.UpdateEmp;

@WebServlet("/update")
public class UpdateEmpServlet extends HttpServlet {
	
	private UpdateEmp service = new UpdateEmp();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		String id = request.getParameter("id"); 
	
		List<Object> params = Arrays.asList(empCode,familyName,lastName,depId,postId,hireDate,birth,id);
		// パラメータのチェック(ラムダ式)
	    boolean hasEmptyParam = params.stream().anyMatch(param -> param == null || param.toString().trim().isEmpty());
	    if(hasEmptyParam) {
	    	// 空のパラメータがある場合は、エラーメッセージを設定して処理を中断
	        request.setAttribute("message", "必須項目に空の値が含まれています。");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
	        dispatcher.forward(request, response);
	    }
		
            try {
				service.updateEmp(params);
				request.setAttribute("message", "社員番号:" + empCode + "の更新ができました");
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				request.setAttribute("message", "不正な入力を検知しました");
				request.setAttribute("id", id);
				String forward = "edit";
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
				dispatcher.forward(request, response);
				return;
			}
            
        String forward = "check";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);
		} else {
	    	response.sendRedirect("start");
	    }
	}

}