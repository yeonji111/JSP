package board;

import java.util.List;

import common.SearchVO;

public interface BoardMapper {
	List<BoardVO> getBoardList(SearchVO vo); //=>  public abstract List<BoardVO< getBoardList() 와 동일 
	BoardVO getBoard(int searchNo); 
	int insertBoard(BoardVO vo);
	int updateBoard(BoardVO vo);
	int deleteBoard(int deleteNo);
}
