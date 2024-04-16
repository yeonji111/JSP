package board;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class BoardService {
	
	BoardDAO dao;
	
	public BoardService(DataSource dataSource) {
		this.dao = new BoardDAO(dataSource);
	}
	
	public List<BoardVO> getBoardList() {
		// 코드가 완성되어 있다고 가정
		return dao.getBoardList();
	}
	public BoardVO getBoard(int searchNo) {
		return dao.getBoard(searchNo);
	}
	
	public void insertBoard(BoardVO vo) {
		dao.insertBoard(vo);
	
	}
	
	public void updateBoard(BoardVO vo) {
		dao.updateBoard(vo);
	}
	
	public void deleteBoard(int deleteNo) {
		dao.deleteBoard(deleteNo);
	}

}