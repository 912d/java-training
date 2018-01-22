package ncdc.training.server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    private void loadData() {
    	log.debug("Loading mocked data");
    	List<Book> books = new ArrayList<Book>();
    	books.add(new Book("It", "Stephen King", "1234567890123"));
    	books.add(new Book("Beginning", "Dan Brown", "0987654321124"));
    	books.add(new Book("Paragraph 22", "Joseph Heller", "098273645813"));
    	jdbcTemplate.batchUpdate("INSERT INTO books(title, author, isbn) VALUES (?,?,?)",
    			books.stream().map(book -> book.toArray()).collect(Collectors.toList()));
    }
    
    @Override
    public void run(String... strings) throws Exception {

        log.debug("Creating tables");
        jdbcTemplate.execute("DROP TABLE books IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE books(" +
                "id SERIAL, title VARCHAR(45), author VARCHAR(45), isbn VARCHAR(13))");
        loadData();
    }
}
