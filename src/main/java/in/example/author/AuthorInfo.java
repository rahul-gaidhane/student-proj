package in.example.author;

import java.util.List;
import java.util.UUID;

import in.example.book.BookInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorInfo {
	
	private UUID id;
	
	private String name;
	
	private String gender;
	
	private List<BookInfo> books;
}
