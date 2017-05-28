package or.kr.connect;

import java.util.Collections;
import java.util.Map;
import java.io.*;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class BookLauncher {

	public static void main(String[] args)   {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/javaweb/db;AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE;");
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");

		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(dataSource);
		String sql = "SELECT COUNT(*) FROM book";
		Map<String, Object> params = Collections.emptyMap();
		Integer count;
		try {
			count = jdbc.queryForObject(sql, params, Integer.class);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		
		System.out.println(count);

	}

}
