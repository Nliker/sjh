package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.dto.BoardDto;
import board.dto.CommentDto;
import board.dto.PageDto;
import dbUtil.DBUtil;

public class BoardDaoImpl implements BoardDao {

	private static BoardDao instance = new BoardDaoImpl();
	private BoardDaoImpl() {}
	public static BoardDao getInstance() {
		return instance;
	}
	
	@Override
	public BoardDto getBoardByNo(int no) throws SQLException {
		try (
				Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"select no, title, user_id,content, date_format(reg_date, '%Y-%m-%d') as reg_date "
					  + "  from "
					  + " board "
					  + " where no=?"
				);
		) {		
				pstmt.setInt(1, no);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					BoardDto board = new BoardDto();
					board.setNo(rs.getInt("no"));
					board.setTitle(rs.getString("title"));
					board.setUserId(rs.getString("user_id"));
					board.setRegDate(rs.getString("reg_date"));
					board.setContent(rs.getString("content"));
					return board;
				}
		}
		return null;
	}
	
	@Override
	public List<BoardDto> selectBoard(PageDto page) throws SQLException {
		List<BoardDto> list = new ArrayList<>();
		try (
				Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"select no, title, user_id,content, date_format(reg_date, '%Y-%m-%d') as reg_date "
					  + "  from "
					  + " board "
					  + " order by no desc "
					  + " limit ?, ?"
				);
		) {		
				pstmt.setInt(1, page.getBegin());
				pstmt.setInt(2, page.getListSize());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					BoardDto board = new BoardDto();
					board.setNo(rs.getInt("no"));
					board.setTitle(rs.getString("title"));
					board.setUserId(rs.getString("user_id"));
					board.setRegDate(rs.getString("reg_date"));
					board.setContent(rs.getString("content"));
					list.add(board);
				}
				return list;
		}
	}

	@Override
	public int selectBoardCount() throws SQLException {
		try (
				Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"select count(*) from board"
				);
		) {
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				return rs.getInt(1);
		}
	}
	
	@Override
	public List<CommentDto> commentList(int boardNo) throws SQLException {
		List<CommentDto> list = new ArrayList<>();
		try (
				Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"select no, board_no, user_id, content, date_format(reg_date, '%Y-%m-%d') as reg_date "
					  + "  from comments "
					  + " where board_no = ? "
				);
		) {		
				pstmt.setInt(1, boardNo);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					CommentDto comment = new CommentDto();
					comment.setNo(rs.getInt("no"));
					comment.setBoardNo(rs.getInt("board_no"));
					comment.setUserId(rs.getString("user_id"));
					comment.setRegDate(rs.getString("reg_date"));
					comment.setContent(rs.getString("content"));
					list.add(comment);
				}
				return list;
		}
	}
	@Override
	public void addComment(CommentDto comment) throws SQLException {
		try (
				Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"insert into comments (board_no, user_id, content) "
					  + "values (?, ?, ?)"
				);
		) {		
			pstmt.setInt(1, comment.getBoardNo());
			pstmt.setString(2, comment.getUserId());
			pstmt.setString(3, comment.getContent());
			pstmt.executeUpdate();
		}
	}


	
}
