package assignment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import assignment.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
