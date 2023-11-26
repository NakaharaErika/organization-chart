package conroller;

import java.io.IOException;

import entity.DBWork;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.CreateEmp;
import service.SecurityService;

@WebServlet("/preCreate")
public class preCreateServlet extends HttpServlet {
	
	 CreateEmp create = new CreateEmp();
	 SecurityService service = new SecurityService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session = request.getSession();
        DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");
        
        
        String view = null;
        if (loggedInUser != null) {
        	
	    	if (request.getAttribute("errormessage") == null) {
	            request.setAttribute("errormessage", "新規登録権限は課長以上のみです");
	        }
	    	//新規作成の権限を確認
	    	try {
				if(service.hasPermission(loggedInUser, "Create Employee")) {
				    	//一番最新の社員番号を取得
						try {
							String empDetails = create.getPreEmp();
							String updateCode = getUpdateCode(empDetails);
							request.setAttribute("empDetails", updateCode);
							view = "/WEB-INF/views/create.jsp";
						} catch (Exception e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						} 
				    } else {
				    	view = "list";
				    }
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
        } else {
        	response.sendRedirect("start");
        }
        
            RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);
	}
	
	private String getUpdateCode(String empCode) {
		String prefix = empCode.substring(0,1);
		String prenumber = empCode.substring(1);
		
		int number = Integer.parseInt(prenumber);
		number++;
		
		String updatedNumber = String.format("%03d", number);
		empCode = prefix + updatedNumber;
		return empCode;
	}


}