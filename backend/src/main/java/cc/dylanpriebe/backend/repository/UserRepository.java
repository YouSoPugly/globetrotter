package cc.dylanpriebe.backend.repository;

import cc.dylanpriebe.backend.objects.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
