package uz.pdp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.entity.AuthUser;

import java.util.Optional;

@Component
public class AuthUserDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




    public void save(AuthUser authUser){

        String sql = "insert into auth_user (full_name, username, password, role) values(?, ?, ?, ?)";
       jdbcTemplate.update(sql, authUser.getFullName(), authUser.getUsername(), authUser.getPassword(), authUser.getRole().toString() );
    }

    public Optional<AuthUser> findByUsername(String username){

       String sql = "select * from auth_user where username=?";
       return Optional.of(jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(AuthUser.class), username ) );

    }

}
