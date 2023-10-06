package board.dao;

import java.sql.SQLException;
import java.util.List;

import board.dto.BoardDto;
import board.dto.CommentDto;
import board.dto.PageDto;

public interface BoardDao {
	List<BoardDto> selectBoard(PageDto search) throws SQLException;
	BoardDto getBoardByNo(int no) throws SQLException;
	int selectBoardCount() throws SQLException;
	List<CommentDto> commentList(int boardNo) throws SQLException;
	void addComment(CommentDto comment) throws SQLException;
}
