package com.fisglobal.postservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fisglobal.postservice.model.CommentsDeo;


@FeignClient(name="comment-service",url="http://localhost:8086/comments")
public interface PostFeignProxy {
	
	@GetMapping("search/post/{pid}")
	public List<CommentsDeo> getByPostId(@PathVariable("pid") int pid);
}
