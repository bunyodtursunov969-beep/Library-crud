package uz.pdp.service;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import uz.pdp.config.security.SessionUser;
import uz.pdp.dao.BookDao;
import uz.pdp.entity.AuthUser;
import uz.pdp.entity.Book;
import uz.pdp.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

@Component
public class BookService {


    private final BookDao bookDao;
    private final SessionUser sessionUser;

    public BookService(BookDao bookDao, SessionUser sessionUser) {
        this.bookDao = bookDao;
        this.sessionUser = sessionUser;
    }

    public List<Book> findAll(){
           return bookDao.findAll();
       }

       public Book findById(Integer id){
         return bookDao.findById(id)
                 .orElseThrow( () -> new NotFoundException( "Book not found with id "+id )  );
       }

    public void save(Book book){
        AuthUser authUser = sessionUser.getAuthUser();
        bookDao.save(book);
    }

    public void delete(Integer id) {
        bookDao.delete(id);
    }
    public void update(Book book){
        bookDao.update(book);
    }
}
