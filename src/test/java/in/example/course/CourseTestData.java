package in.example.course;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import com.github.javafaker.Faker;

public class CourseTestData {

	public static List<CourseInfo> getCourseInfos(int count) {
		Faker faker = new Faker();
		
		List<CourseInfo> courseInfo = new ArrayList<>();
		
		IntStream.range(0, count).boxed().forEach(i -> {
			CourseInfo info = new CourseInfo();
			info.setId(UUID.randomUUID());
			info.setName(faker.book().title());
			
			courseInfo.add(info);
		});
		
		return courseInfo;
	}

}
