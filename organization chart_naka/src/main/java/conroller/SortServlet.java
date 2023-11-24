package conroller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import entity.DBWork;
import entity.DepartmentWork;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.GetEmpListById;
import service.SecurityService;

@WebServlet("/sortList")
public class SortServlet extends HttpServlet {
	
	GetEmpListById list = new GetEmpListById();
	SecurityService service = new SecurityService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sort時の飛び先
		 HttpSession session = request.getSession();
	     DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");
	     List<DepartmentWork> departments = (List<DepartmentWork>) session.getAttribute("departments");
	     
	 if (loggedInUser != null) {
		 
		 Integer depId = Integer.parseInt(request.getParameter("depId"));//部署
		 String item = request.getParameter("items");//社員番号or役職
		 String sort = request.getParameter("sort");//昇降順
		 //部署名
		 String depName = findDepNameById(depId, departments);
		 
		 	//権限の確認
			
			try {
				//編集権限の確認
				String ediFlg = (service.hasPermission(loggedInUser, "CRUD All Tables"))? "1":"";
				request.setAttribute("ediFlg", ediFlg);
				//削除権限の確認
				String delFlg = (service.hasPermission(loggedInUser, "CRUD All Tables"))? "1":"";
				request.setAttribute("delFlg", delFlg);
			 
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			
	    	//並び替え後に発動 調べたい部署IDと並び替え順（社員番号or役職　昇降順）を入れて得られたリストを格納
	    	List<HashMap<String, String>> empList;
			try {
				//もし、「全部署」が選択されていた場合
				if(depId == 0) {
					empList = list.getEmpListAll();
				} else {
					empList = list.getEmpListByTag(depId,item,sort);
				}
				request.setAttribute("message", "現在の表示：" + depName + " を " + sortItem(item) + " 順に " + sortStr(sort) + " で表示しています");
				request.setAttribute("rows", empList);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}      
	 } else {
		response.sendRedirect("start");
    }
	 	
	    String view = "/WEB-INF/views/list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);   
	}
	
	//部署名を取り出すメソッド
	private String findDepNameById(int depId, List<DepartmentWork> departments) {
	    if (depId == 0) {
	        return "全部署";
	    }
	    if (departments != null) {
	        for (DepartmentWork department : departments) {
	            if (depId == department.getDepId()) {
	                return department.getDepName();
	            }
	        }
	    }
	    return "不明な部署";
	}
	
	//社員番号OR役職（増えたら後ろに増やす）
	protected String sortItem(String item) {
		switch(item) {
		case "Post.post_id":
			return "役職";
		case "emp_code":
			return "社員番号";
		default:
			return "";
		}
	}
	
	//並び順の取り出し
	protected String sortStr(String sort) {
		return sort.equals("asc")? "昇順" : "降順";
	}
}
