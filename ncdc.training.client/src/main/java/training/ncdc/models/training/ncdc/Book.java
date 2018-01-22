package training.ncdc;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class Book {

    @NotNull
    @Size(min=2, max=30)
    private String title;

	@NotNull
    @Size(min=2, max=30)
	@Pattern(regexp="A\\w* A\\w*")
    private String author;
	
	@NotNull
    @Size(min=13, max=13)
    private String isbn;
    
    public Book() {}
    public Book(String title, String author, String isbn) {
    	this.title = title;
    	this.author = author;
    	this.isbn = isbn;
    }
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
