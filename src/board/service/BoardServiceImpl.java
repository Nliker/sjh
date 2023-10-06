package board.service;

import java.util.*;

import board.dao.BoardDao;
import board.dao.BoardDaoImpl;
import board.dto.BoardDto;
import board.dto.CommentDto;
import board.dto.PageDto;
import board.dto.PageResult;

public class BoardServiceImpl implements BoardService{
	
	private BoardDao dao;
	private static BoardService instance = new BoardServiceImpl();
	private BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}
	public static BoardService getInstance() {
		return instance;
	}
	@Override
	public Map<String, Object> listBoard(PageDto page) throws Exception {
		List<BoardDto> list = dao.selectBoard(page);
		int count = dao.selectBoardCount();
		PageResult pg = new PageResult(page.getPageNo(), count);
		
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		result.put("pg", pg);
		return result;
	}
	
	@Override
	public BoardDto getBoardByNo(int no) throws Exception {
		return dao.getBoardByNo(no);
	}
	
	@Override
	public Map<String,Object> commentList(int boardNo) throws Exception {
		List<CommentDto> list = dao.commentList(boardNo);
		
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		return result;
	}
	
	@Override
	public void addComment(CommentDto comment) throws Exception {
		dao.addComment(comment);
	}
}
