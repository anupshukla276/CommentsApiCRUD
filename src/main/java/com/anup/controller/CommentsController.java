package com.anup.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anup.model.Comments;
import com.anup.repository.CommentsRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.persistence.Id;

@RestController
@RequestMapping("/api")
public class CommentsController {
	@Autowired
	CommentsRepository commentsRepository;
	
	
	@PostMapping("/submitcomments")
	public String createNewComments(@RequestBody Comments comments) {
		commentsRepository.save(comments);
		return "Comments is Stored";
	}

	@GetMapping("/getcomments")
	public List<Comments> getComments() {
		return commentsRepository.findAll();

	}

	@GetMapping("/getcomments/{Id}")
	public Comments getCommentsById(@PathVariable Long Id) {
		Optional<Comments> commentsOptional = commentsRepository.findById(Id);
		if (commentsOptional.isPresent()) {
			return commentsOptional.get();
		} else
			return null;
	}

	@PutMapping("/comments/{id}")
	public Comments updateCommentsById(@PathVariable Long id, @RequestBody Comments updatedComment) {
	    // Find the existing comment by ID 
	    Comments existingComment = commentsRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Comment not found"));

	    // Update the existing comment with the details from the updated comment object
	    existingComment.setDate(updatedComment.getDate());
	    existingComment.setcommented_by(updatedComment.getcommented_by());
	    existingComment.setText(updatedComment.getText());

	    // Save and return the updated comment
	    return commentsRepository.save(existingComment);
	}




	@DeleteMapping("/deletecomment/{Id}")
	public String deleteCommentById(@PathVariable Long Id) {
		 commentsRepository.deleteById(Id);
		return "Deleted Successfully";

	}
	
	@SuppressWarnings("deprecation")
	@GetMapping("filtercomment/{date}")
	public List<Comments> filterCommentByDate(@PathVariable String date) {
       List<Comments> commentList = commentsRepository.findAll();
       List<Comments> filteredComments = new ArrayList<>();

       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
       try {
           Date filterDate = dateFormat.parse(date);
           for (Comments comment : commentList) {
               if (comment.getDate() != null && comment.getId() != null && comment.getText() != null
                       && comment.getcommented_by() != null) {
                   if (comment.getDate().compareTo(filterDate) == 0) {
                       filteredComments.add(comment);
                   }
               }
           }
       } catch (ParseException e) {
           // Handle parsing exception
           e.printStackTrace();
       }

       return filteredComments;
   }
	@SuppressWarnings("deprecation")
	@GetMapping("filtercomment/byCommentedBy/{commented_by}")
	public List<Comments> filterCommentByCommentedBy(@PathVariable String commented_by) {
	    List<Comments> commentList = commentsRepository.findAll();
	    List<Comments> filteredComments = new ArrayList<>();

	    for (Comments comment : commentList) {
	        if (comment.getcommented_by() != null && comment.getcommented_by().equals(commented_by)) {
	            filteredComments.add(comment);
	        }
	    }

	    return filteredComments;
	}
		
   
   
	}
	

