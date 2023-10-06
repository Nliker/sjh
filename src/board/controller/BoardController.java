package board.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import board.dto.BoardDto;
import board.dto.CommentDto;
import board.dto.PageDto;
import board.service.BoardService;
import board.service.BoardServiceImpl;
import memberDto.MemberDto;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private BoardService service;

	public BoardController() {
		service = BoardServiceImpl.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if ("mvBoard".equals(action)) {
			mvBoard(req, resp);
		} else if ("mvPage".equals(action)) {
			mvPage(req, resp);
		} else if ("getComments".equals(action)) {
			getComments(req, resp);
		} else if ("registComments".equals(action)) {
			registComment(req, resp);
		}
	}

	private void registComment(HttpServletRequest req, HttpServletResponse resp) {
		BufferedReader br;
		StringBuilder sb = new StringBuilder();
		try {
			InputStream inputStream = req.getInputStream();
			if (inputStream != null) {
				br = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = br.read(charBuffer)) > 0) {
					sb.append(charBuffer, 0, bytesRead);
				}
			}

			String str = sb.toString();
			
			JSONParser parser = new JSONParser();
	        JSONObject jsonObject = (JSONObject) parser.parse(str);
			int boardNo = Integer.parseInt((String) jsonObject.get("boardNo"));
			String content = (String) jsonObject.get("content");

			HttpSession session = req.getSession();
			MemberDto member = (MemberDto) session.getAttribute("memberInfo");
			String id = member.getId();
			
			CommentDto comment = new CommentDto();
			comment.setBoardNo(boardNo);
			comment.setUserId(id);
			comment.setContent(content);
			
			service.addComment(comment);
			
//			service.addComment(comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getComments(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int boardNo = Integer.parseInt(req.getParameter("boardNo"));
			Gson gson = new Gson();
			String json = gson.toJson(service.commentList(boardNo));
			System.out.println(json);
			resp.setContentType("application/json;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.write(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private void mvPage(HttpServletRequest req, HttpServletResponse resp) {
		PageDto page = new PageDto(
				req.getParameter("pageNo") == null ? 1 : Integer.parseInt(req.getParameter("pageNo")));
		try {
			req.setAttribute("result", service.listBoard(page));
			req.getRequestDispatcher("/board/list.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void mvBoard(HttpServletRequest req, HttpServletResponse resp) {
		int no = Integer.parseInt(req.getParameter("detail"));
		try {
			BoardDto board = service.getBoardByNo(no);
			req.setAttribute("board", board);

			RequestDispatcher rd = req.getRequestDispatcher("/board/view.jsp");
			rd.forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
