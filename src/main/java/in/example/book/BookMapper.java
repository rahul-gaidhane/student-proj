package in.example.book;

public class BookMapper {
	
	public static BookInfo toBookInfo(Book book) {
		BookInfo bookInfo = new BookInfo();
		
		bookInfo.setDateOfPublish(book.getDateOfPublish());
		bookInfo.setId(book.getId());
		bookInfo.setName(book.getName());
		bookInfo.setPublicationHouse(book.getPublicationHouse());
		
		return bookInfo;
	}
}
