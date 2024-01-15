package Items.Shop.Repository;

import Items.Shop.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository  extends JpaRepository<Users, Integer> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM Users u WHERE u.Username = ?1")
    boolean existsByUsername(String Username);

    @Query("SELECT u FROM Users u WHERE u.Username = :Username")
     Users findByUsername(String Username);


}
