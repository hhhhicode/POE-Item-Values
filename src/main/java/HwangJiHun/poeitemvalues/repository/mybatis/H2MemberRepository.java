package HwangJiHun.poeitemvalues.repository.mybatis;

import HwangJiHun.poeitemvalues.model.members.Member;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class H2MemberRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public H2MemberRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Member> findById(Long id) {
        String sql = "SELECT * FROM members WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id);

        return Optional.of(jdbcTemplate.queryForObject(sql, param, getMemberRowMapper()));
    }

    public Optional<Member> findByUserId(String userid) {
        String sql = "SELECT * FROM members WHERE user_id = :userId";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userId", userid);
        Member member = jdbcTemplate.queryForObject(sql, param, getMemberRowMapper());
        return Optional.of(jdbcTemplate.queryForObject(sql, param, getMemberRowMapper()));
    }

    private static BeanPropertyRowMapper<Member> getMemberRowMapper() {
        return BeanPropertyRowMapper.newInstance(Member.class);
    }
}
