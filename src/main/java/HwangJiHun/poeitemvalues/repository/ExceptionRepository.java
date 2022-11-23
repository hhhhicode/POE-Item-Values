package HwangJiHun.poeitemvalues.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ExceptionRepository {

    private final JdbcTemplate jdbcTemplate;

    public ExceptionRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(Exception e) {
        String sql = "insert into exceptions (PROJECT_ID, DATE, ERROR_MESSAGE) values (?, ?, ?)";
    }
}
