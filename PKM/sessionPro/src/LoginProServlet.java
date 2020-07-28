

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginProServlet
 */
@WebServlet("/loginPro")
public class LoginProServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginProServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String addr=request.getParameter("addr");
		String email=request.getParameter("email");
		String hp=request.getParameter("hp");
		if(id!=null && id.equals("abcd")) {
			HttpSession session=request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("pw", pw);
			session.setAttribute("addr", addr);
			session.setAttribute("email", email);
			session.setAttribute("hp", hp);
			response.sendRedirect("result.jsp");
		}else {
			response.sendRedirect("login2.jsp?msg="+URLEncoder.encode("로그인 실패","utf-8"));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
