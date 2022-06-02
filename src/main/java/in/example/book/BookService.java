package in.example.book;

import java.util.List;
import java.util.UUID;

import in.example.author.AuthorInfo;

public interface BookService {
	
	public List<AuthorInfo> findAuthorsByBook(UUID bookId);
}
