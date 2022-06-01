package in.example.course;

import java.util.List;
import java.util.UUID;

import in.example.book.BookInfo;

public interface CourseService {
	
	List<CourseInfo> findAll();

	List<BookInfo> findBooksByCourse(UUID courseId);
}
