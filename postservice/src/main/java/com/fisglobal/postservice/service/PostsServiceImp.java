package com.fisglobal.postservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fisglobal.postservice.dao.PostsDao;
import com.fisglobal.postservice.model.CommentsDeo;
import com.fisglobal.postservice.model.Posts;

@Service
public class PostsServiceImp implements PostsService{
	
	
	@Autowired
	PostFeignProxy proxy; 
	
	@Autowired
	RestTemplate templete;
	
	@Autowired
	PostsDao dao;

	@Override
	public List<Posts> getAllPosts() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Posts addPost(Posts p) {
		// TODO Auto-generated method stub
		return dao.save(p);
	}

	@Override
	public String deeltePost(int id) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public List<Posts> searchPostByTitle(String title) {
		// TODO Auto-generated method stub
		List<Posts>post= new ArrayList<Posts>();
		post=dao.findAll();
		List<Posts>result= new ArrayList<Posts>();
		for(Posts p:post) {
			if(p.getTitle().equals(title))
				result.add(p);
		}
		return result;
	}
	
	@Override
	public List<Posts> searchPostByAuthor(String author) {
		// TODO Auto-generated method stub
		List<Posts>post= new ArrayList<Posts>();
		post=dao.findAll();
		List<Posts>result= new ArrayList<Posts>();
		for(Posts p:post) {
			if(p.getAuthor().equals(author))
				result.add(p);
		}
		return result;
	}

	@Override
	public List<CommentsDeo> getCommentsByPost(int id) {
		// TODO Auto-generated method stub
		return templete.getForObject("http://comment-service/comments/search/post/"+id, List.class);
//		return proxy.getByPostId(id);
	}
	
	

	
}
