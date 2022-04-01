package com.fisglobal.postservice.controler;

import java.util.ArrayList;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fisglobal.postservice.model.CommentsDeo;
import com.fisglobal.postservice.model.Posts;
import com.fisglobal.postservice.service.PostFeignProxy;
import com.fisglobal.postservice.service.PostsService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("posts")
public class PostsControler {
	
	Logger logger=Logger.getLogger(PostsControler.class);
	
	@Autowired
	PostsService service;
	
	@GetMapping("all")
	public ResponseEntity<List<Posts>> showAll(){
		return new ResponseEntity<List<Posts>>(service.getAllPosts(),HttpStatus.OK);
	}
	
	@PostMapping("add")
	public Posts insertUsers(@RequestBody Posts p){
		return service.addPost(p);
	}

	@GetMapping("search/title/{title}")
	public ResponseEntity<List<Posts>> showPostByTitle(@PathVariable("title") String title){
		return new ResponseEntity<List<Posts>>(service.searchPostByTitle(title),HttpStatus.OK);
	}
	
	@GetMapping("search/author/{author}")
	public ResponseEntity<List<Posts>> showPostByAuthor(@PathVariable("author") String author){
		return new ResponseEntity<List<Posts>>(service.searchPostByAuthor(author),HttpStatus.OK);
	}
	
	@GetMapping("search/comments/{pid}")
	@Retry(name="default")
	public ResponseEntity<List<CommentsDeo>> showCommentsByPostId(@PathVariable("pid") int pid){
		
		logger.info("Running kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
		return new ResponseEntity<List<CommentsDeo>>(service.getCommentsByPost(pid),HttpStatus.OK);
	}
	
	public ResponseEntity<List<CommentsDeo>> sendDummyData(@PathVariable int postId){
		CommentsDeo comment1 = new CommentsDeo(7,5,"kishore kumar","need more on java");
		CommentsDeo comment2 = new CommentsDeo(9,6,"sunil krishna","very basic info");
		List<CommentsDeo> data = new ArrayList();
		data.add(comment1);
		data.add(comment2);
		return new ResponseEntity(data, HttpStatus.OK);
	}
}
