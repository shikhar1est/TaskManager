package com.project.TaskManager.Repositories;

import com.project.TaskManager.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
