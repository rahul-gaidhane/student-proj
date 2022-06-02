package in.example.book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import com.github.javafaker.Faker;

public class BookTestData {

	public static List<BookInfo> getBookInfos(int count) {
		
		Faker faker = new Faker();
		
		List<BookInfo> bookInfos = new ArrayList<>();
		
		IntStream.range(0, count).boxed().forEach(i -> {
			BookInfo bookInfo = new BookInfo();
			bookInfo.setCourseId(UUID.randomUUID());
			bookInfo.setDateOfPublish(LocalDate.now());
			bookInfo.setId(UUID.randomUUID());
			bookInfo.setName(faker.name().fullName());
			bookInfo.setPublicationHouse(faker.company().name());
			
			bookInfos.add(bookInfo);
			
		});
		
		return bookInfos;
	}
	
	
}
