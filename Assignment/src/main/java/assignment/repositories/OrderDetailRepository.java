package assignment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import assignment.entities.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
	@Query("SELECT o FROM OrderDetail o WHERE o.order.account.id = :id")
	public List<OrderDetail> findById(int id);
}
