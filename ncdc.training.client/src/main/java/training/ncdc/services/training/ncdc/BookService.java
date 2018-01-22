package training.ncdc;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import training.ncdc.Book;

@Component
public class BookService implements IBookService {

	private static final String ADDRESS = "http://localhost:8090/api/";
	private static final String ADD = ADDRESS + "add";
	private static final String ALL = ADDRESS + "all";
	private final RestTemplate restTemplate = new RestTemplate();
	private static final IBookService instance = new BookService();

	@Bean
	public IBookService getInstance() {
		return instance;
	}
	
	private BookService() {}
	
	@Override
	public void add(Book book) {
		restTemplate.postForObject(ADD, book, Book.class);
	}

	@Override
	public List<Book> all() {
		return restTemplate.getForObject(ALL, List.class);
	}

}
