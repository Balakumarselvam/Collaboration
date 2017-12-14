package com.dao;

import java.util.List;

import com.model.BlogComment;
import com.model.BlogPost;

public interface BlogPostDao {
	void saveBlogPost(BlogPost blogPost);
	List<BlogPost> getAllBlogs(int approved);
	BlogPost getBlogPost(int id);
	void updateBlogPost(BlogPost blogPost);
	List<BlogPost> getApprovalStatus(String username);
	void addBlogComment(BlogComment blogComment);
	List<BlogComment> getBlogComments(int blogPostId);
	void updateViewedStatus(List<BlogPost> blogPosts);
}
