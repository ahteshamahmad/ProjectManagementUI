package com.projectify.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectify.Model.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

}
