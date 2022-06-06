package in.example.author;

import org.mapstruct.Mapper;

import in.example.book.BookMapper;

@Mapper(uses = BookMapper.class)
public interface AuthorMapper {
	
	public AuthorInfo toAuthorInfo(Author auth);
}
