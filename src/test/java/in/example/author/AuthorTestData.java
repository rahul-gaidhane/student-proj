package in.example.author;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import com.github.javafaker.Faker;

import in.example.book.BookTestData;

public class AuthorTestData {

	public static List<AuthorInfo> getAuthorInfos(int count) {
		
		Faker faker = new Faker();
		
		List<AuthorInfo> infos = new ArrayList<>();
		
		IntStream.range(0, count).boxed().forEach(i -> {
			AuthorInfo info = new AuthorInfo();
			
			info.setBooks(List.of(BookTestData.getBookInfos(1).get(0)));
			info.setGender("male");
			info.setId(UUID.randomUUID());
			info.setName(faker.name().fullName());
			
			infos.add(info);
		});
		
		
		return infos;
	}

}
