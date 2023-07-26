package com.example.demo.repository;

import com.example.demo.model.EmployeeEntity;
import jakarta.persistence.MapKey;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
  @Query( value = "SELECT * FROM employee e " +
      "WHERE (:firstname IS NULL OR e.firstname ILIKE CONCAT('%', :firstname, '%')) " +
      "AND (:lastname IS NULL OR e.lastname ILIKE CONCAT('%', :lastname, '%')) " +
      "AND (:sex IS NULL OR e.sex= :sex)" +
      "AND (:post IS NULL OR e.post ILIKE CONCAT('%', :post, '%')) " +
      "AND (cast(:entranceDateStart as date) IS NULL OR e.entrance_date >= :entranceDateStart) " +
      "AND (cast(:entranceDateEnd as date) IS NULL OR e.entrance_date <= :entranceDateEnd) " +
      "AND (cast(:exitDateStart as date) IS NULL OR e.exit_date >= :entranceDateStart) " +
      "AND (cast(:exitDateEnd as date) IS NULL OR e.exit_date <= :exitDateEnd) ;"  ,nativeQuery = true)
  List<EmployeeEntity> getEmployeeEntitiesWithFilter(@Param("firstname")String firstname,
                                         @Param("lastname")String lastname,
                                         @Param("post")String post,
                                         @Param("sex")String sex,
                                         @Param("entranceDateStart") LocalDate entranceDateStart,
                                         @Param("entranceDateEnd")LocalDate entranceDateEnd,
                                         @Param("exitDateStart") LocalDate exitDateStart,
                                         @Param("exitDateEnd")LocalDate exitDateEnd,
                                         Pageable pageable);
}
