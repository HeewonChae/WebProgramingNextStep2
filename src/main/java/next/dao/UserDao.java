package next.dao;

import java.sql.ResultSet;
import java.util.List;

import next.model.User;
import next.support.jdbctemplate.JdbcTemplate;
import next.support.jdbctemplate.RowMapper;

public class UserDao {

	public void insert(User user) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate();

		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		jdbcTemplate.excuteInsertQuery(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}

	public void update(User user) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate();

		String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userid=?";
		jdbcTemplate.excuteUpdateQuery(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
	}

	public List<User> findAll() {
		
		RowMapper<User> rm = (ResultSet rs) -> {
			return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
					rs.getString("email"));
		};

		String sql = "SELECT userId, password, name, email FROM USERS";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		
		return jdbcTemplate.executeSelectsQuery(sql, rm);
	}

	public User findByUserId(String userId) {
		
		RowMapper<User> rm = (ResultSet rs) -> {
			return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
					rs.getString("email"));
		};

		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		
		return jdbcTemplate.executeSelectQuery(sql, rm, userId);
	}
}
