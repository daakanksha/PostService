package com.fisglobal.postservice.service;

import java.util.List;

import com.fisglobal.postservice.model.CommentsDeo;
import com.fisglobal.postservice.model.Posts;

public interface PostsService {
	
	public List<Posts> getAllPosts();
	
	public Posts addPost(Posts u);
	
	public String deeltePost(int id);
	
	public List<Posts> searchPostByTitle(String title);
	public List<Posts> searchPostByAuthor(String author);
	public List<CommentsDeo>  getCommentsByPost(int id);
	

}
