package com.anup.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.anup.model.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Long> {


}
