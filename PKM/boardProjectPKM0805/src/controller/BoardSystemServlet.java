package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.BoardDao;
import dao.MemberDao;
import dao.ReplyDao;
import vo.Board;
import vo.Member;
import vo.PageMaker;
import vo.Reply;

/**
 * Servlet implementation class BoardSystemServlet
 */
@WebServlet("*.do")
public class BoardSystemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FILE_REPO="E:\\src\\jsp_pro\\boardProject\\WebContent\\images\\temp";

    /**
     * Default constructor. 
     */
    public BoardSystemServlet() {
        // TODO Auto-generated constructor stub
    }
    
    private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		String requestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String action=requestURI.substring(contextPath.length());
		if(action.equals("/list.do")) {
			
			String strPage=request.getParameter("pageNum");
			int pageNum=1;
			if(strPage!=null) {
				pageNum=Integer.parseInt(strPage);
			}
			BoardDao boardDao=BoardDao.getInstance();
			int totalCount=boardDao.getBoardCount();
			PageMaker pageM=new PageMaker(pageNum,totalCount);
			List<Board> list=boardDao.selectAll(pageM.getStart(),pageM.getEnd());
			request.setAttribute("pageM", pageM);
			request.setAttribute("list", list);
			request.getRequestDispatcher("board/list.jsp")
			.forward(request, response);
			
		}else if(action.equals("/writeForm.do")) {
			request.getRequestDispatcher("board/write.jsp")
			.forward(request, response);
			
		}else if(action.equals("/write.do")) {
			Map<String,String> boardMap=upload(request,response);
			String title=boardMap.get("title");
			String content=boardMap.get("content");
			String writer=boardMap.get("writer");
			String image_name=boardMap.get("filename");
			boolean flag=BoardDao.getInstance().insertBoard(new Board(title,content,writer,image_name));
			if(flag) {
				out.print("<script>alert('새글을 추가했습니다.');location.href='list.do';</script>");
			}else {
				out.print("<script>alert('새글 추가 실패했습니다.');location.href='writeForm.do';</script>");
			}
			
		}else if(action.equals("/read.do")) {
			
			int bno=Integer.parseInt(request.getParameter("bno"));
			boolean flag=BoardDao.getInstance().updateReadCount(bno);
			Board board=BoardDao.getInstance().selectOne(bno);
			List<Reply> replyList=ReplyDao.getInstance().selectReply(bno);
			if(board!=null && flag==true) {
				request.setAttribute("replyList", replyList);
				request.setAttribute("board", board);
				request.getRequestDispatcher("board/read.jsp")
				.forward(request, response);
			}else {
				out.print("<script>alert('게시글 조회 실패.');location.href='list.do';</script>");
			}
			
		}else if(action.equals("/updateForm.do")) {
			int bno=Integer.parseInt(request.getParameter("bno"));
			Board board=BoardDao.getInstance().selectOne(bno);
			if(board!=null) {
				request.setAttribute("board", board);
				request.getRequestDispatcher("board/update.jsp")
				.forward(request, response);
			}else {
				out.print("<script>alert('게시글 읽기 실패.');location.href='read.do?bno="+bno+"';</script>");
			}
			
		}else if(action.equals("/update.do")) {
			Map<String,String> boardMap=upload(request,response);
			int bno=Integer.parseInt(boardMap.get("bno"));
			String title=boardMap.get("title");
			String content=boardMap.get("content");
			String writer=boardMap.get("writer");
			String image_name=boardMap.get("filename");
			String pageNum=boardMap.get("pageNum");
			if(image_name == null) {
				image_name=boardMap.get("ex_filename");
			}
			boolean flag=BoardDao.getInstance().updateBoard(new Board(bno, title,content,writer,image_name));
			if(flag) {
				out.print("<script>alert('글을 수정했습니다.');location.href='list.do?pageNum="+pageNum+"';</script>");
			}else {
				out.print("<script>alert('글수정을 실패했습니다.');location.href='updateForm.do?bno="+bno+"&pageNum="+pageNum+"';</script>");
			}
		}else if(action.equals("/delete.do")) {
			String pageNum=request.getParameter("pageNum");
			int bno=Integer.parseInt(request.getParameter("bno"));
			boolean flag=BoardDao.getInstance().deleteBoard(bno);
			if(flag) {
				out.print("<script>alert('글을 삭제했습니다.');location.href='list.do?pageNum="+pageNum+"';</script>");
			}else {
				out.print("<script>alert('글삭제를 실패했습니다.');location.href='read.do?bno="+bno+"&pageNum="+pageNum+"';</script>");
			}
		}else if(action.equals("/loginForm.do")) {
			
		}else if(action.equals("/login.do")) {
			String id=request.getParameter("id");
			String pw=request.getParameter("pw");
			int n=MemberDao.getInstance().login(id,pw);
			if(n==1) {
				HttpSession session=request.getSession();
				session.setAttribute("session_id", id);
				out.print("success");
			}else if(n==0) {
				out.print("password error");
			}else {
				out.print("id error");
			}
		}else if(action.equals("/logout.do")) {
			HttpSession session=request.getSession();
			session.removeAttribute("session_id");
			out.print("success");
			//response.sendRedirect("list.do");
		}else if(action.equals("/joinForm.do")) {
			request.getRequestDispatcher("member/join.jsp")
			.forward(request, response);
		}else if(action.equals("/overappedId.do")) {
			String id=request.getParameter("id");
			boolean flag=MemberDao.getInstance().overappedId(id);
			if(flag) {
				out.print("not usable");
			}else {
				out.print("usable");
			}
		}else if(action.equals("/join.do")) {
			String id=request.getParameter("id");
			String pw=request.getParameter("pw");
			String name=request.getParameter("name");
			boolean flag=MemberDao.getInstance().insert(new Member(id,pw,name));
			if(flag) {
				out.print("<script>alert('회원가입 성공');location.href='list.do';</script>");
			}else {
				out.print("<script>alert('회원가입 실패');location.href='joinForm.do';</script>");
			}
		}else if(action.equals("/replyAdd.do")) {
			int bno=Integer.parseInt(request.getParameter("bno"));
			String writer=request.getParameter("writer");
			String replytext=request.getParameter("replytext");
			boolean flag=ReplyDao.getInstance()
					.insertReply(new Reply(bno,writer,replytext));
			if(flag) {
				out.print("success");
			}else {
				out.print("error");
			}
		}else if(action.equals("/replyDelete.do")) {
			int rno=Integer.parseInt(request.getParameter("rno"));
			boolean flag=ReplyDao.getInstance().replyDelete(rno);
			if(flag) {
				out.print("success");
			}else {
				out.print("error");
			}
		}
	}
    
    private Map<String,String> upload(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
    	Map<String,String> boardMap=new HashMap<String,String>();
    	String encoding="utf-8";
    	File currentDirPath=new File(FILE_REPO);
    	DiskFileItemFactory factory=new DiskFileItemFactory();
    	factory.setRepository(currentDirPath);
    	factory.setSizeThreshold(5*1024*1024);
    	
    	factory.setDefaultCharset(encoding);
    	
    	ServletFileUpload upload=new ServletFileUpload(factory);
    	
    	try {
    		List<FileItem> items=upload.parseRequest(request);
    		for(int i=0; i<items.size();i++) {
    			FileItem item=(FileItem)items.get(i);
    			if(item.isFormField()) {
    				System.out.println(item.getFieldName()+":"+item.getString());
    				boardMap.put(item.getFieldName(), item.getString());
    			}else {
    				System.out.println("파라미터명:"+item.getFieldName());
    				System.out.println("파일명:"+item.getName());
    				System.out.println("파일의 크기:"+item.getSize());
    				
    				if(item.getSize()>0) {
    					int idx=item.getName().lastIndexOf("\\");
    					if(idx==-1) {
    						idx=item.getName().lastIndexOf("/");
    					}
    					String fileName=item.getName().substring(idx+1);
    					File uploadFile=new File(currentDirPath+"\\"+fileName);
    					
    					boardMap.put(item.getFieldName(), fileName);
    					item.write(uploadFile);
    				}
    			}
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	
    	return boardMap;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}

}
