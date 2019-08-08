package telran.java29.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.java29.forum.dto.DatePeriodDto;
import telran.java29.forum.dto.NewCommentDto;
import telran.java29.forum.dto.NewPostDto;
import telran.java29.forum.dto.PostDto;
import telran.java29.forum.dto.PostUpdateDto;
import telran.java29.forum.service.ForumService;

@RestController
@RequestMapping("/forum")
public class ForumController {
	@Autowired
	ForumService service;

	@PostMapping("/post")
	public PostDto addPost(@RequestBody NewPostDto newPost) {
		return service.addNewPost(newPost);
	}

	@GetMapping("/post/{id}")
	public PostDto getPost(@PathVariable String id) {
		return service.getPost(id);
	}

	@DeleteMapping("/post/{id}")
	public PostDto removePost(@PathVariable String id) {
		return service.removePost(id);
	}

	@PutMapping("/post")
	public PostDto updatePost(@RequestBody PostUpdateDto postUpdateDto) {
		return service.updatePost(postUpdateDto);
	}

	@PutMapping("/post/{id}/like")
	public boolean addLike(@PathVariable String id) {
		return service.addLike(id);
	}

	@PutMapping("/post/{id}/comment")
	public PostDto addComment(@PathVariable String id, @RequestBody NewCommentDto newCommentDto) {
		return service.addComment(id, newCommentDto);
	}

	@PostMapping("/posts/tags")
	public Iterable<PostDto> getPostsByTags(@RequestBody List<String> tags) {
		return service.findPostsByTags(tags);
	}

	@GetMapping("/posts/author/{author}")
	public Iterable<PostDto> getPostsByAuthor(@PathVariable String author) {
		return service.findPostsByAuthor(author);
	}

	@PostMapping("/posts/period")
	public Iterable<PostDto> getPostsBetweenDate(@RequestBody DatePeriodDto periodDto) {
		return service.findPostsByDates(periodDto);
	}

}
