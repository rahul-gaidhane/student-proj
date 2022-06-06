package in.example.book;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {
	
	public BookInfo toBookInfo(Book book);
	
	public List<BookInfo> toBookInfo(List<Book> book);
}
