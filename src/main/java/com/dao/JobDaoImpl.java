package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Job;
@Repository
@Transactional
public class JobDaoImpl implements JobDao {
@Autowired
private SessionFactory sessionFactory;
	public void saveJob(Job job) {
		Session session=sessionFactory.getCurrentSession();
		session.save(job);
	}

	public List<Job> getAllJob() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Job");
		List<Job> jobs=query.list();
		int size=jobs.size();
		System.out.println("no of jobs created"+size);
		return jobs;
	}

	public Job getJobById(int id) {
		Session session=sessionFactory.getCurrentSession();
		Job job=(Job) session.get(Job.class,id);
		return job;
	}

}
