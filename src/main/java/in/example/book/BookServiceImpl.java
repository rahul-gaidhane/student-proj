package in.example.book;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.example.author.Author;
import in.example.author.AuthorInfo;
import in.example.author.AuthorMapper;
import in.example.author.AuthorRepository;
import in.example.config.UtilityService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

	private AuthorRepository authorRepository;
	
	@Autowired
	public BookServiceImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}
	
	@PostConstruct
	public void postBean() {
		
		log.debug("Service to perform operations on course service bean creation...");
		
		List<Author> authors = authorRepository.findAll();
		
		AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);
		
		UtilityService.authorInfos = authors.stream().map(authorMapper::toAuthorInfo).collect(Collectors.toList());
	}
	
	@Override
	public List<AuthorInfo> findAuthorsByBook(UUID bookId) {
		log.debug("Service to find authors by book id : {}", bookId);
		
		List<AuthorInfo> authorInfos = UtilityService.findAuthorsByBook(bookId);
		
		return authorInfos;
	}

}
