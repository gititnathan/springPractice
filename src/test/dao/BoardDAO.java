package test.dao;

import java.util.List;

import test.dto.BoardDBBean;

public interface BoardDAO {
	public List<BoardDBBean> listBoard();
	public BoardDBBean getBoard(int num, String mode);
	public int insertBoard(BoardDBBean dto);
	public int deleteBoard(int num, String passwd);
	public int updateBoard(BoardDBBean dto);
}
