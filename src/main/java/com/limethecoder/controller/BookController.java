package com.limethecoder.controller;

import com.limethecoder.controller.editor.AuthorEditor;
import com.limethecoder.controller.editor.GenreEditor;
import com.limethecoder.controller.editor.PublisherEditor;
import com.limethecoder.data.domain.Author;
import com.limethecoder.data.domain.Book;
import com.limethecoder.data.domain.Genre;
import com.limethecoder.data.domain.Publisher;
import com.limethecoder.data.service.AuthorService;
import com.limethecoder.data.service.BookService;
import com.limethecoder.data.service.GenreService;
import com.limethecoder.data.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookService bookService;
    private PublisherService publisherService;
    private GenreService genreService;
    private AuthorService authorService;

    @Autowired
    private AuthorEditor authorEditor;

    @Autowired
    private PublisherEditor publisherEditor;

    @Autowired
    private GenreEditor genreEditor;

    @Autowired
    public BookController(BookService bookService, PublisherService publisherService,
                          GenreService genreService, AuthorService authorService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.genreService = genreService;
        this.publisherService = publisherService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Author.class, authorEditor);
        binder.registerCustomEditor(Genre.class, genreEditor);
        binder.registerCustomEditor(Publisher.class, publisherEditor);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String authorProfile(@PathVariable int id, Model model) {
        Book book = bookService.findOne(id);
        model.addAttribute("book", book);
        return "book";
    }

    @RequestMapping(method=RequestMethod.GET)
    public List<Book> books(
            @RequestParam(value="cnt", defaultValue="10") int cnt) {
        return bookService.findBooksLimit(cnt);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showForm(Model model) {
        model.addAttribute(new Book());
        List<Publisher> publishers = publisherService.findAll();
        model.addAttribute("publishers", publishers);
        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "addBook";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String submit(@ModelAttribute("book") Book book,
                         BindingResult result,
                         @RequestPart(value = "image") MultipartFile image,
                         HttpServletRequest request)
            throws IOException {
        if (result.hasErrors()) {
            System.out.println(result);
            return "error";
        }

        if(image != null && !image.isEmpty()) {
            String realPath = request.getServletContext()
                    .getRealPath("/") + image.getOriginalFilename();
            image.transferTo(new File(realPath));
            book.setIconUrl("/" + image.getOriginalFilename());
        }
        Book saved = bookService.add(book);

        return "redirect:/books/" + saved.getId();
    }
}
