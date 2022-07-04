package assignment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import assignment.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
