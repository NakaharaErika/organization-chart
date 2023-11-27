//https://docs.google.com/presentation/d/1BCwOQNUL9-ogwd40dvbEIemkbvKmzYB0TuYTJQ4eVf4/edit#slide=id.p
//↑今回の状態遷移図もどき＆テーブル構成

package conroller;

import java.io.IOException;
import java.util.List;

import entity.DBWork;
import entity.DepartmentWork;
import entity.PostWork;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.CheckFalseCount;
import service.WorkService;

@WebServlet("/start")
public class StartServlet extends HttpServlet {
	
	WorkService service = new WorkService();
	CheckFalseCount check = new CheckFalseCount();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログイン画面を表示
		String view = "/WEB-INF/views/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    // ユーザーから送信された社員番号とパスワードを取得する。
	    String empCode = request.getParameter("user_id");
	    String password = request.getParameter("password");
	    String view = null;
	    
	    if (!empCode.trim().isEmpty() && !password.trim().isEmpty()) {
	    
	    //社員番号が存在するかチェック
	    try {
			if(service.checkempCodeExist(empCode)) {
				//アカウントがロックされていないかチェック
				if(!check.isAccountLocked(empCode)){
			    	//パスワードが一致しているかチェック
			    	DBWork dbWork = service.login(empCode, password);//一致していたらDBWorkクラスにempCode,name,family_name,last_name,dep_id,post_id,id(主キー）をセット
			    	if(dbWork != null) {//ログイン成功
			    		//パスワードが一致していたら、フラグを０に戻してdbWrokに値を設定
				    	check.countReset(empCode);
				    	HttpSession session = request.getSession();//パスワード以外の情報をセッションとして登録
				        session.setAttribute("loggedInUser", dbWork);
				        // 部署情報を取得してセッションに保存
				        List<DepartmentWork> departments = service.getAllDepartments();
				        session.setAttribute("departments", departments);
				        // 役職情報を取得してセッションに保存
				        List<PostWork> posts = service.getAllPosts();
				        session.setAttribute("posts", posts);
				        
				        view = "/check";
			    	} else {//ログイン失敗
			    		//一致していなかったら失敗カウントをカウントアップ
			    		boolean canRetry = check.incrementFalseCnt(empCode);
			    		//失敗カウントを調べて,3回未満ならtrue、3回以上ならfalseを返す。
			    		String falseMessage = canRetry? "IDかパスワードが異なります":"３回間違えたのでロックしました";
			    		request.setAttribute("message", falseMessage);
			    		view = "/WEB-INF/views/login.jsp";
			    	}
				} else {
					//ロックされていたらエラーメッセージを表示
					request.setAttribute("message", "このアカウントはロックされています");
					view = "/WEB-INF/views/login.jsp";
				}
			} else {
				//IDが存在しない場合
				String falseMessage = "IDが存在しません";
				request.setAttribute("message", falseMessage);
			    view = "/WEB-INF/views/login.jsp";
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	    } else {
	    	//入力していない場合
	    	String falseMessage = "ゆーざーIDとパスワードを入力してください";
	    	request.setAttribute("message", falseMessage);
	        view = "/WEB-INF/views/login.jsp";
	    }
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	    
	}
}
