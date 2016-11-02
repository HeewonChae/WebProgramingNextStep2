package next.support.jdbctemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;

public class JdbcTemplate {
	public void excuteInsertQuery(String sql, Object... parameters) {
		excuteUpdateQuery(sql, parameters);
	}
	
	public void excuteUpdateQuery(String sql, Object... parameters) {

        try(Connection con = ConnectionManager.getConnection();
        		PreparedStatement pstmt = con.prepareStatement(sql);) {
            
            for(int i = 0; i < parameters.length; ++i)
            	pstmt.setObject(i+1, parameters[i]);

            pstmt.executeUpdate();
        } catch(SQLException e){
        	throw new DataBaseException(e);
        }
	}
	
	public <T> T executeSelectQuery(String sql, RowMapper<T> rm, Object... parameters) {
		List<T> list = executeSelectsQuery(sql, rm, parameters);
		
		if(list.size() > 0)
			return list.get(0);
		else
			return null;
    }
	
	public <T> List<T> executeSelectsQuery(String sql, RowMapper<T> rm, Object... parameters) {
		
		ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection();
        		PreparedStatement pstmt = con.prepareStatement(sql);){
            
            for(int i = 0; i < parameters.length; ++i)
            	pstmt.setObject(i+1, parameters[i]);

            rs = pstmt.executeQuery();

            List<T> list = new ArrayList<>();
            if (rs.next()) {
            	list.add(rm.mapRow(rs));
            }

            return list;
        } catch(SQLException e){
        	throw new DataBaseException(e);
        }finally{
        	if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					throw new DataBaseException(e);
				}
        }
    }
}
