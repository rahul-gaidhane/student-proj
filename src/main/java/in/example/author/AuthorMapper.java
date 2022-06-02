package in.example.author;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AuthorMapper {
	
	public static AuthorInfo toAuthorInfo(Author auth) {
		AuthorInfo info = new AuthorInfo();
		info.setGender(auth.getGender());
		info.setId(auth.getId());
		info.setName(auth.getName());
		
		List<UUID> bookIds = auth.getBooks().stream().map(book -> book.getId()).collect(Collectors.toList());
		
		info.setBookIds(bookIds);
		
		return info;
	}
}
