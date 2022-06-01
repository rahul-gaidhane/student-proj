package in.example.book;

import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {
	
	public BookInfo toBookInfo(Book book);
}
