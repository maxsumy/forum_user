package telran.java29.forum.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java29.forum.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {

	List<Post> findByTagsIn(List<String> tags);

	List<Post> findByAuthor(String author);

	List<Post> findByDateCreatedBetween(LocalDate from, LocalDate to);

}
