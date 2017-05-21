package io.github.lowering.repository;

import io.github.lowering.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/5/19.
 */
@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String username);
}
