package com.springboot.project.book.model.dao;

import com.springboot.project.book.model.bo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookDao extends JpaRepository<Book, String> {
    Optional<Book> findById(String id);
    List<Book> findByIdInAndIsCancelFalse(List<String> uuidList);
}
