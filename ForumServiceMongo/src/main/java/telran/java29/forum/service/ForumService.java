package telran.java29.forum.service;

import java.util.List;

import telran.java29.forum.dto.DatePeriodDto;
import telran.java29.forum.dto.NewCommentDto;
import telran.java29.forum.dto.NewPostDto;
import telran.java29.forum.dto.PostDto;
import telran.java29.forum.dto.PostUpdateDto;

public interface ForumService {

	PostDto addNewPost(NewPostDto newPost);

	PostDto getPost(String id);

	PostDto removePost(String id);

	PostDto updatePost(PostUpdateDto postUpdateDto);

	boolean addLike(String id);

	PostDto addComment(String id, NewCommentDto newCommentDto);

	Iterable<PostDto> findPostsByTags(List<String> tags);

	Iterable<PostDto> findPostsByAuthor(String author);

	Iterable<PostDto> findPostsByDates(DatePeriodDto periodDto);

}
