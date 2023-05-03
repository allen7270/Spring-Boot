package com.springboot.project.a.model.dao;

import com.springboot.project.a.model.bo.ProjectA1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectA1Dao extends JpaRepository<ProjectA1, String> {
}
