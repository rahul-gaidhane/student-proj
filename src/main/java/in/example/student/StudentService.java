package in.example.student;

import org.springframework.data.domain.Page;

public interface StudentService {

	StudentCreateResponse create(StudentCreateRequest request);

	Page<StudentInfo> findByCourseName(StudentFilterRequest request);
}
