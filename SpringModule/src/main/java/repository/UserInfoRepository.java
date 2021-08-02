package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

}
