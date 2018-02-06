package training.ncdc;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import training.ncdc.IBookService;

@Controller
public class BookController {

	private static final Logger log = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private IBookService bookService;

    @RequestMapping("/books")
    public String list(Model model) {
    	log.info("Entering books");
    	List<Book> books = bookService.all();
    	model.addAttribute("books", books);
        model.addAttribute("isEmpty", books.isEmpty());
        return "books";
    }

    @RequestMapping("/add")
    public String add(Model model) {
    	log.info("Entering add");
    	model.addAttribute("book", new Book());
    	return "add";
    }
    
    @PostMapping("/add")
    public String greetingSubmit(@ModelAttribute @Valid Book book, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
    		log.error("Incorrect book's data");
            return "add";
        }
    	bookService.add(book);
    	log.info("Redirecting to books");
        return "redirect:/books";
    }
}