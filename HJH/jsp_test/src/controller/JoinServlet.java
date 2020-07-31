package controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import vo.MemberVO;

@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("test02/joinForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String name=request.getParameter("name");
		String gender=request.getParameter("gender");
		String job=request.getParameter("job");
		String intro=request.getParameter("intro");
		boolean flag = MemberDao.getInstance()
				.insert(new MemberVO(id,pw,name,gender,job,intro));
		if(flag==true) {
			response.sendRedirect("list.do");
		}else {
			String msg = URLEncoder.encode("insert 실패","utf-8");
			response.sendRedirect("join.do?msg="+msg);
		}
	}

}
