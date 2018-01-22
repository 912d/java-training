package training.ncdc;

import java.util.List;

import training.ncdc.Book;

public interface IBookService {

	void add(Book book);
	List<Book> all();
}
