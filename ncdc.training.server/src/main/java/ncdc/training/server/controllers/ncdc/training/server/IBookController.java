package ncdc.training.server;

import java.util.List;

public interface IBookController {

	void addBook(Book book);
	List<Book> getAllBooks();
}
