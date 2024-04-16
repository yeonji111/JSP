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
							b.name as wirter,
							a.title,
							a.content,
							a.create_date,
							a.modify_date,
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
				String content = resultSet.getString("content");
				Date createDate = resultSet.getDate("create_date");
				Date modifyDate = resultSet.getDate("modify_date");
				int hits = resultSet.getInt("hits");

				list.add(new BoardVO(no, writer, title, content, createDate.toLocalDate(), modifyDate.toLocalDate(),
						hits));
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

	public void insertBoard(BoardVO vo) {

	}

	public void updateBoard(BoardVO vo) {

	}

	public void deleteBoard(int deleteNo) {

	}

}
