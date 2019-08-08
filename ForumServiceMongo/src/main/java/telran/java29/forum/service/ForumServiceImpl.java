package telran.java29.forum.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java29.forum.dao.PostRepository;
import telran.java29.forum.domain.Comment;
import telran.java29.forum.domain.Post;
import telran.java29.forum.dto.CommentDto;
import telran.java29.forum.dto.DatePeriodDto;
import telran.java29.forum.dto.NewCommentDto;
import telran.java29.forum.dto.NewPostDto;
import telran.java29.forum.dto.PostDto;
import telran.java29.forum.dto.PostUpdateDto;
import telran.java29.forum.exceptions.BadDateFormatException;

@Service
public class ForumServiceImpl implements ForumService {
	
	@Autowired
	PostRepository repository;

	@Override
	public PostDto addNewPost(NewPostDto newPost) {
		Post post = new Post(newPost.getTitle(), newPost.getContent(),
				newPost.getAuthor(), newPost.getTags());
		post = repository.save(post);
		return convertToPostDto(post);
	}

	@Override
	public PostDto getPost(String id) {
		Post post = repository.findById(id).orElse(null);
		return post == null ? null : convertToPostDto(post);
	}

	@Override
	public PostDto removePost(String id) {
		Post post = repository.findById(id).orElse(null);
		if (post != null) {
			repository.delete(post);
		}
		return post == null ? null : convertToPostDto(post);
	}

	@Override
	public PostDto updatePost(PostUpdateDto postUpdateDto) {
		Post post = repository.findById(postUpdateDto.getId()).orElse(null);
		if (post != null) {
			String content = postUpdateDto.getContent();
			if (content != null) {
				post.setContent(content);
			}
			String title = postUpdateDto.getTitle();
			if (title != null) {
				post.setTitle(title);
			}
			Set<String> tags = postUpdateDto.getTags();
			if (tags != null) {
				tags.forEach(post::addTag);
			}
			repository.save(post);
		}
		return post == null ? null : convertToPostDto(post);
	}

	@Override
	public boolean addLike(String id) {
		Post post = repository.findById(id).orElse(null);
		if (post != null) {
			post.addLike();
			repository.save(post);
			return true;
		}
		return false;
	}

	@Override
	public PostDto addComment(String id, NewCommentDto newCommentDto) {
		Post post = repository.findById(id).orElse(null);
		if(post != null) {
			post.addComment(convertToComment(newCommentDto));
			repository.save(post);
			return convertToPostDto(post);
		}
		return null;
	}

	@Override
	public Iterable<PostDto> findPostsByTags(List<String> tags) {
		return repository.findByTagsIn(tags).stream()
					.map(this::convertToPostDto)
					.collect(Collectors.toList());
	}

	@Override
	public Iterable<PostDto> findPostsByAuthor(String author) {
		return repository.findByAuthor(author).stream()
				.map(this::convertToPostDto)
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PostDto> findPostsByDates(DatePeriodDto periodDto) {
		try {
			LocalDate from = LocalDate.parse(periodDto.getFrom());
			LocalDate to = LocalDate.parse(periodDto.getTo());
			return repository.findByDateCreatedBetween(from, to).stream()
					.map(this::convertToPostDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new BadDateFormatException();
		}
	}
	
	private PostDto convertToPostDto(Post post) {
		return PostDto.builder()
				.id(post.getId())
				.author(post.getAuthor())
				.title(post.getTitle())
				.dateCreated(post.getDateCreated())
				.content(post.getContent())
				.tags(post.getTags())
				.likes(post.getLikes())
				.comments(post.getComments().stream().map(this::convertToCommentDto).collect(Collectors.toSet()))
				.build();
	}
	
	private Comment convertToComment(NewCommentDto newCommentDto) {
		return new Comment(newCommentDto.getUser(), newCommentDto.getMessage());
	}
	
	private CommentDto convertToCommentDto(Comment comment) {
		return CommentDto.builder()
				.user(comment.getUser())
				.message(comment.getMessage())
				.dateCreated(comment.getDateCreated())
				.likes(comment.getLikes())
				.build();
	}

}
