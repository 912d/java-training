package ncdc.training.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class BookController implements IBookController {

	private static final Logger log = LoggerFactory.getLogger(BookController.class);

	@Autowired
    JdbcTemplate jdbcTemplate;
	 
	@Override
	@PostMapping("/add")
	@ResponseBody
	public void addBook(@RequestBody Book book) {
		log.info("adding book " + book.toString());
		jdbcTemplate.update("INSERT INTO books(title, author, isbn) VALUES (?,?,?)", book.toArray());
	}

	@Override
	@GetMapping("/all")
	@ResponseBody
	public List<Book> getAllBooks() {
		log.info("getting all books");
    	return jdbcTemplate.query("SELECT title, author, isbn FROM books", new RowMapper<Book>() {
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            	return new Book(rs.getString(1), rs.getString(2), rs.getString(3));
            }
    	});
	}
}
