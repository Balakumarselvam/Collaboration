package com.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.JobDao;
import com.dao.UserDao;
import com.model.Job;
import com.model.User;
import com.model.Error;

@Controller
public class JobController {
	@Autowired
	private JobDao jobDao;
	@Autowired
	private UserDao userDao;
	@RequestMapping(value="/savejob",method=RequestMethod.POST)
	public ResponseEntity<?> saveJob(@RequestBody Job job,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"unauthorized user");
			return new ResponseEntity<Error> (error,HttpStatus.UNAUTHORIZED);
		}
		String username=(String) session.getAttribute("username");
		User user=userDao.getUserByUsername(username);
		if(user.getRole().equals("ADMIN"))
		{
			try{
				job.setPostedOn(new Date());
				jobDao.saveJob(job);
				return new ResponseEntity<Job> (job,HttpStatus.OK);
			}catch(Exception e){
				Error error=new Error(5,"unable to insert job details");
				return new ResponseEntity<Error> (error,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else{
			Error error=new Error(6,"access denied");
			return new ResponseEntity<Error> (error,HttpStatus.UNAUTHORIZED);
		}
	}
	@RequestMapping(value="/getalljob",method=RequestMethod.GET)
	public ResponseEntity<?> getAllJob(HttpSession session){
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"unauthorized user");
			return new ResponseEntity<Error> (error,HttpStatus.UNAUTHORIZED);
		}
		List<Job> jobs=jobDao.getAllJob();
		return new ResponseEntity<List<Job>> (jobs,HttpStatus.OK);
	}
	@RequestMapping(value="/getjobbyid/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getJobById(@PathVariable int id,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"unauthorized user");
			return new ResponseEntity<Error> (error,HttpStatus.UNAUTHORIZED);
		}
		Job job=jobDao.getJobById(id);
		return new ResponseEntity<Job> (job,HttpStatus.OK);

	}
}
