package com.dantesoft.sire_todos.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dantesoft.sire_todos.entity.Todo;

@Repository	
public interface TodoRepository extends JpaRepository<Todo, UUID>{
	   @Query("SELECT t FROM Todo t WHERE LOWER(t.description) LIKE LOWER(CONCAT('%', :search, '%'))")
	    Page<Todo> findByDescriptionContaining(@Param("search") String search, Pageable pageable);
}
