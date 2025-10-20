package com.zexson.signor_p.UserModule;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Object> {
    public User findByEmailAndPassword(String email, String password);
    public User findByEmail(String email);
}
