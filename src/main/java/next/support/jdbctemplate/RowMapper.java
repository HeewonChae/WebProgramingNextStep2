package next.support.jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper<T> {
	public abstract T mapRow(ResultSet rs) throws SQLException;
}
