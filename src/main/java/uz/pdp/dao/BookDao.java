package uz.pdp.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.entity.Book;

import java.util.List;
import java.util.Optional;


@Component
public class BookDao {


    private final JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> findAll(){
      return jdbcTemplate.query("select * from books", new BeanPropertyRowMapper<>(Book.class) );
    }

    public void save(Book book){
        jdbcTemplate.update("insert into books (title, pages) values (?, ?)",
                book.getTitle(), book.getPages() );
    }

    public void delete(Integer id){
        jdbcTemplate.update("delete from books where id = ?", id );
    }

    public Optional<Book> findById(Integer id){
        try{
           Book book = jdbcTemplate.queryForObject("select * from books where id = ?", new BeanPropertyRowMapper<>(Book.class), id);
           return Optional.of(book);
        } catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    public void update(Book book){
        jdbcTemplate.update("update books set title = ?, pages = ? where id = ?", book.getTitle(), book.getPages(), book.getId() );
    }
}
