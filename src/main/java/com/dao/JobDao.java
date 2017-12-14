package com.dao;

import java.util.List;

import com.model.Job;

public interface JobDao {
	void saveJob(Job job);
	List<Job> getAllJob();
	Job getJobById(int id);
}
