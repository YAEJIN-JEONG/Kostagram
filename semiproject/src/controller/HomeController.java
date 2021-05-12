package controller;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardDAO;
import model.MemberVO;
import model.PostVO;


public class HomeController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("mvo")==null){
			request.setAttribute("url", "/register/login.jsp");
			return "/template/layout.jsp";
		}
		else {
			MemberVO mvo = (MemberVO)session.getAttribute("mvo");
			ArrayList<PostVO> list = BoardDAO.getInstance().getPostingTotalList();
			ArrayList<String> list2 = BoardDAO.getInstance().listLikes(mvo.getUserEmail());
			//ArrayList<String> list3 = new ArrayList<String> ();
//			for (int i = 0;i<list.size() ;i++) {
//				list3.add(BoardDAO.getInstance().countLikes(list.get(i).getNo()));
//			}
			
			for (int i = 0;i<list.size() ;i++) {
				list.get(i).setLikeCount((BoardDAO.getInstance().countLikes(list.get(i).getNo())));
			}			
			
			// top3 
			ArrayList<PostVO> list4 = BoardDAO.getInstance().getPostingTop3List();
			
			
			
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			//request.setAttribute("list3", list3);
			request.setAttribute("list4", list4);
			request.setAttribute("url", "/main/home.jsp");  
			//request.setAttribute("url", "/main/homeTest.jsp"); 
			
			return "/template/layout.jsp";
		}

	}

}
