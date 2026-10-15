package uz.pdp.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.dao.BookDao;
import uz.pdp.dto.BookCreateDto;
import uz.pdp.entity.Book;
import uz.pdp.service.BookService;


@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }




    @GetMapping("/book/create")
    public String createBookPage( Model model ){
        model.addAttribute("bookDto", new BookCreateDto() );
        return "book_create";
    }

    @PostMapping("/book/create")
    public String createBook(@Valid @ModelAttribute("bookDto") BookCreateDto dto, BindingResult bindingResult){
        if ( bindingResult.hasErrors() ){
            return "book_create";
        }
          bookService.save(Book.builder()
                  .title( dto.getTitle() )
                  .pages( dto.getPages() )
                  .build() );
        return "redirect:/";
    }

    @GetMapping("/book/delete/{id}")
      public String deleteBook(@PathVariable("id") Integer id){
         bookService.delete(id);
         return "redirect:/";
    }




    @GetMapping("/get-book/{id}")
    @ResponseBody
    public String getBookById(@PathVariable("id") Integer id){
        Book book = bookService.findById(id);
      return book.getTitle();
    }




   @GetMapping("/book/update/{id}")
    public ModelAndView bookUpdatePage(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book_update");
       modelAndView.addObject("book", bookService.findById(id) );
       return modelAndView;
   }

   @PostMapping("/book/update/{id}")
    public String updateBook(@PathVariable("id") Integer id, @RequestParam("title") String title, @RequestParam("pages") Integer pages ){
          bookService.update( new Book(id, title, pages) );
        return "redirect:/";
   }
}
