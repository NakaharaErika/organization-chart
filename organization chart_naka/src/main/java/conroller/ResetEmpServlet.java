package conroller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ResetEmp;
import service.WorkService;

@WebServlet("/reset")
public class ResetEmpServlet extends HttpServlet {
	
	ResetEmp reset = new ResetEmp();
	WorkService service = new WorkService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    // ユーザーから送信されたユーザーIDとパスワードを取得する。
		String empCode = request.getParameter("empCode");
	    String crrEmp = request.getParameter("crrEmp");
	    String password = request.getParameter("password");
	    String view = null;
	    
	    try {
	    	//入力値と編集対象者の社員番号が一致しているかチェック
	    	if(empCode.equals(crrEmp)){
		    	//ユーザーIDが存在するかチェック
				if(service.checkempCodeExist(crrEmp)) {
					if( password != null) {
					    //パスワードをリセットする
				    	reset.ResetPass(crrEmp,password);
				    	request.setAttribute("message", "新しいパスワードをセットしました");
				    	view = "/WEB-INF/views/login.jsp";
					} else {
						request.setAttribute("message", "パスワードを入力してください");
				    	view = "/WEB-INF/views/reset.jsp";
					}
				} else {
					//IDが存在しない場合
					String falseMessage = "IDが存在しません";
					request.setAttribute("message", falseMessage);
				    view = "/WEB-INF/views/reset.jsp";
				}
	    	}else {
	    		//削除対象が一致しない場合
				String falseMessage = "削除対象と社員番号が一致していません";
				request.setAttribute("message", falseMessage);
			    view = "/WEB-INF/views/reset.jsp";
	    	}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	    
	}
}
