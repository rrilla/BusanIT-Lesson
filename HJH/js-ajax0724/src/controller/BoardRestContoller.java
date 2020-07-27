package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.BoardDao;
import vo.Board;

@WebServlet("/api/board") // 실행하기위한 url주소
public class BoardRestContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// SELECT로 가져올떄
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet() 호출됨");

		String strBno = request.getParameter("bno");
		String strJson = "";
		BoardDao boardDao = BoardDao.getInstance();
		if (strBno == null) {
			boardDao = BoardDao.getInstance();
			List<Board> list = boardDao.selectAll();

			// 데이터 응답 포맷 2가지 - JSON, XML
			// 자바 객체의 데이터를 JSON 포맷 문자열로 변환하기
			Gson gson = new Gson();
			strJson = gson.toJson(list);
			System.out.println(strJson); // 서버에출력

		} else { // strBno != bull
			int bno = Integer.parseInt(strBno);

			boardDao = BoardDao.getInstance();
			Board board = boardDao.selectOne(bno);

			Gson gson = new Gson();
			strJson = gson.toJson(board);
			System.out.println(strJson);
		}
		// 클라이언트로 출력하기
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(strJson); // 정상응답은 200번 코드 사용
		out.close();
	}

	// INSERT
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8"); // 응답에서 최종적 인코딩방식 설정,jsp 설정에도 있음
		PrintWriter out = response.getWriter();

		System.out.println("doPost() 호출됨");

		String title = request.getParameter("title");// == null ? "" : request.getParameter("title");
		String content = request.getParameter("content");// == null ? "" : request.getParameter("content");
		String writer = request.getParameter("writer");// == null ? "" : request.getParameter("writer");

		if (title.equals("") || content.equals("") || writer.equals("")) {
			response.setStatus(500); // 오류 메서드,에러응답은 500번 코드(약속)
			// response.setContentType("text/html; charset=UTF-8");
			// PrintWriter out = response.getWriter();
			return;
		}

		Board board = new Board(writer, title, content);

		BoardDao boardDao = BoardDao.getInstance();
		boardDao.insertBoard(board);

		/*
		 * response.setContentType("text/html; charset=UTF-8"); //응답에서 최종적 인코딩방식 설정,jsp
		 * 설정에도 있음 PrintWriter out = response.getWriter();
		 */
		out.print("ㅊㅋ성공"); // 정상응답은 200번 코드(약속)
		// out.flush(); //출력스트림 버퍼 비우기 , 닫을때 알아서 실행됨.
		out.close();
	}

	// UPDATE
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPut() 호출됨");
		
		String bno = request.getParameter("bno");// == null ? "" : request.getParameter("title");
		String title = request.getParameter("title");// == null ? "" : request.getParameter("title");
		String content = request.getParameter("content");// == null ? "" : request.getParameter("content");
		String writer = request.getParameter("writer");// == null ? "" : request.getPar
		
		Board board = new Board();
		board.setBno(Integer.parseInt(bno));
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(writer);
		
		BoardDao boardDao = BoardDao.getInstance();
		boardDao.update(board);
		
		response.setContentType("text/html; charset=UTF-8"); // 응답에서 최종적 인코딩방식 설정,jsp 설정에도 있음
		PrintWriter out = response.getWriter();
		out.print("ㅊㅋ글 수정성공"); 
		out.close();
	}

	// DELETE
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doDelete() 호출됨");
		
		String[] strArrBno = request.getParameterValues("bno");
		
		BoardDao boardDao = BoardDao.getInstance();
		
		for (String strBno : strArrBno) {
			int bno = Integer.parseInt(strBno);
			boardDao.delete(bno);
		}
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		out.print("ㅊㅋㅊㅋ삭제성공");
		out.close();
		
	}

}
