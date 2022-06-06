package be.pxl.auctions.dao;

import be.pxl.auctions.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findUserByEmail(String email);
	Optional<User> findUserById(long userId);
	List<User> findAll();
}
