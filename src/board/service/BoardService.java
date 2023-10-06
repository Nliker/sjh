package board.service;

import java.util.*;

import board.dto.BoardDto;
import board.dto.CommentDto;
import board.dto.PageDto;

public interface BoardService {
	 Map<String,Object> listBoard(PageDto page) throws Exception;
	 BoardDto getBoardByNo(int no) throws Exception;
	 Map<String,Object> commentList(int boardNo) throws Exception;
	 void addComment(CommentDto comment) throws Exception;
}
