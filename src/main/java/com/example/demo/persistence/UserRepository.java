package com.example.demo.persistence;

import com.example.demo.entity.humans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("from User c where c.firstName = :firstName AND c.lastName = :lastName")
    public Optional<User> findUserByFirstNameAndLastName(@Param("firstName") String firstName,
                                                  @Param("lastName") String lastName);

    @Query("from User c where c.email = :email")
    public Optional<User> findUserByEmail(@Param("email") String email);

}
