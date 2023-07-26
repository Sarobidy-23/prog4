package com.example.demo.repository;

import com.example.demo.model.EmployeeEntity;
import jakarta.persistence.MapKey;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
  @Query( value = "SELECT * FROM employee e WHERE " +
      "(:firstname is null or e.firstname ilike concat('%', :firstname, '%')) " +
      "AND (:lastname is null or e.lastname ilike concat('%', :lastname, '%')) "+
      "AND (:post is null or e.post ilike concat('%', :post, '%'))" +
      "AND (:sex is null or e.sex= :sex); ",nativeQuery = true)
  List<EmployeeEntity> getEmployeeEntitiesWithFilter(@Param("firstname")String firstname,
                                         @Param("lastname")String lastname,
                                         @Param("post")String post,
                                         @Param("sex")String sex,
                                         Pageable pageable);
}
