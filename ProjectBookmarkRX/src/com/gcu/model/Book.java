package com.gcu.model;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/** Contains all variables related to books.
 *  This includes: title, author, pagesRead, pagesTotal, startDate, publishDate, finishedCheck, and bookID.
 * @author Joe Leon | Jerran Fredricks
 * @since 11-4-18
 * @category Model
 */
public class Book 
{
	

	@NotNull(message="Title cannont be null.")
	@Size(min=5, max=50, message="Title must be between 10 and 50 characters.")
	private String title;
	@NotNull(message="Author cannont be null.")
	@Size(min=5, max=50, message="Author must be between 10 and 50 characters.")
	private String author;
	@NotNull(message="Pages Read cannont be null.")
	private int pagesRead;
	@NotNull(message="Pages cannont be null.")
	private int pagesTotal;
	@NotNull(message="Date cannont be null.")
	@Size(min=10, max=11, message="Date must be formated as DD/MM/xxxx")
	private String startDate;
	@NotNull(message="Date cannont be null.")
	@Size(min=10, max=11, message="Date must be formated as DD/MM/xxxx")
	private String lastDate;
	@NotNull(message="Date cannont be null.")
	@Size(min=10, max=11, message="Date must be formated as DD/MM/xxxx")
	private String publishDate;
	private int finishedCheck;
	private int bookID;

	/** Constructor.
	 * 
	 * @param title
	 * @param author
	 * @param pagesRead
	 * @param pagesTotal
	 * @param startDate
	 * @param publishDate
	 * @param bookID
	 */
	public Book(String title, String author, int pagesRead, int pagesTotal, String lastDate, String startDate, String publishDate, int bookID) {
		super();
		this.title = title;
		this.author = author;
		this.pagesRead = pagesRead;
		this.pagesTotal = pagesTotal;
		this.startDate = startDate;
		this.lastDate = lastDate;
		this.publishDate = publishDate;
		this.finishedCheck = 0;
		this.bookID = bookID;
		
	}
	
	/**Other constructor.
	 * 
	 */
	public Book()
	{
		title = "XxxxX";
		author = "XxxxX";
		pagesRead = 0;
		pagesTotal = 0;
		startDate = "xx/xx/xxxx";
		lastDate = "xx/xx/xxxx";
		publishDate = "xx/xx/xxxx";
		finishedCheck =0;
		bookID =0;
	}
	//=== Getters and Setters ===
	
	
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
	public int getPagesRead() {
		return pagesRead;
	}
	public void setPagesRead(int pagesRead) {
		this.pagesRead = pagesRead;
	}
	public int getPagesTotal() {
		return pagesTotal;
	}
	public void setPagesTotal(int pagesTotal) {
		this.pagesTotal = pagesTotal;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public int getFinishedCheck() {
		return finishedCheck;
	}

	public void setFinishedCheck(int finishedCheck) {
		this.finishedCheck = finishedCheck;
	}
	public String toString() {
		return this.title+", "+this.author+", "+this.pagesRead+", "+this.pagesTotal+", "+this.startDate+", "+this.lastDate+", "+this.publishDate;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
}
