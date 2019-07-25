package com.noyo.example.crud.repositories;

import com.noyo.example.crud.models.User;
import com.noyo.example.crud.models.UserIdVersionCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, UserIdVersionCompositeKey> {
    // Queries to get * by just id go here
//    @Transactional
//    void deleteById(Integer id);

    @Transactional
    List<User> findAllById(Integer id);
}
