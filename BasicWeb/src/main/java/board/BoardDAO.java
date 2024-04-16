package board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class BoardDAO {
	private DataSource dataSource;

	public BoardDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<BoardVO> getBoardList() {

		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			String sql = """
					select
							a.no,
							b.name writer,
							a.title,
							a.create_date,
							a.hits
					from
						board a
						inner join member b on a.writer = b.id

					""";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int no = resultSet.getInt("no");
				String writer = resultSet.getString("writer");
				String title = resultSet.getString("title");
				Date createDate = resultSet.getDate("create_date");
				int hits = resultSet.getInt("hits");
				list.add(new BoardVO(no, writer, title, createDate.toLocalDate(), hits));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
					statement.close();
					resultSet.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public BoardVO getBoard(int searchNo) {
		return null;
	}

	public int insertBoard(BoardVO vo) {
		int executeUpdate = 0; // 0으로 초기화해야한다.
		try {
			Connection connection = dataSource.getConnection();
			String sql = "INSERT INTO board (writer,title,content) VALUES (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, vo.getWriter());
			statement.setString(2, vo.getTitle());
			statement.setString(3, vo.getContent());

			executeUpdate = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return executeUpdate;
	}

	public int updateBoard(BoardVO vo) {
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int executeUpdate = 0;
		try {
//			select
			connection = dataSource.getConnection();
			String sql = 
					"""
					select no, writer,title,content
						from member m, board b
						where b.writer = m.id and b.no = ?
					""";
			statement.setInt(1, vo.getNo());
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int no = resultSet.getInt("no");
				String writer = resultSet.getString("writer");
				String title = resultSet.getString("title");
				String content = resultSet.getString("content");
				
				list.add(new BoardVO(no,writer,title,content));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		int updateBoard = service.updateBoard(new BoardVO(updateNo,writer,title,content));
//		업데이트 Update
		try {
			connection = dataSource.getConnection();
			String updateSql = "Update board set title = ?, content = ?, modify_date = sysdate, writer = ? where no = ? ";
			statement = connection.prepareStatement(updateSql);
			statement.setString(1, vo.getTitle());
			statement.setString(2, vo.getContent());
			statement.setString(2, vo.getWriter());
			statement.setInt(4, vo.getNo());
			
			
			
			executeUpdate = statement.executeUpdate();
			System.out.println(executeUpdate);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return executeUpdate;
		
		
		
	}

	public int deleteBoard(int deleteNo) {
		int executeUpdate = 0; // 0으로 초기화해야한다.
		try {
			Connection connection = dataSource.getConnection();
			String sql = "DELETE from board where no = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, deleteNo);

			executeUpdate = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return executeUpdate;
	}

}
