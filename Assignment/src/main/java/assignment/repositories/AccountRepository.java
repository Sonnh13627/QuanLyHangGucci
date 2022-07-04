package assignment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import assignment.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	@Query("SELECT acc FROM Account acc WHERE acc.email LIKE :email")
	public Account findByEmail(String email);
}
