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
      "AND (:sex IS NULL OR e.sex ILIKE CONCAT('%', :sex, '%'))" +
      "AND (:post IS NULL OR e.post ILIKE CONCAT('%', :post, '%')) " +
      "AND (cast(:entranceDateStart as date) IS NULL OR e.entrance_date >= :entranceDateStart) " +
      "AND (cast(:entranceDateEnd as date) IS NULL OR e.entrance_date <= :entranceDateEnd) " +
      "AND (cast(:exitDateStart as date) IS NULL OR e.exit_date >= :entranceDateStart) " +
      "AND (cast(:exitDateEnd as date) IS NULL OR e.exit_date <= :exitDateEnd)" +
      "ORDER BY " +
      " CASE WHEN :firstnameOrder = 'desc' THEN e.firstname END DESC," +
      " CASE WHEN :firstnameOrder = 'asc' THEN e.firstname END asc ," +
      " CASE WHEN :lastnameOrder = 'desc' THEN e.lastname END DESC," +
      " CASE WHEN :lastnameOrder = 'asc' THEN e.lastname END asc," +
      " CASE WHEN :sexOrder = 'desc' THEN e.sex END DESC," +
      " CASE WHEN :sexOrder = 'asc' THEN e.sex END asc," +
      " CASE WHEN :postOrder = 'desc' THEN e.post END DESC," +
      " CASE WHEN :postOrder = 'asc' THEN e.post END asc," +
      " CASE WHEN :entranceDateOrder = 'desc' THEN e.entrance_date END DESC," +
      " CASE WHEN :entranceDateOrder = 'asc' THEN e.entrance_date END asc," +
      " CASE WHEN :exitDateOrder = 'desc' THEN e.exit_date END DESC," +
      " CASE WHEN :exitDateOrder = 'asc' THEN e.exit_date END asc"
      ,nativeQuery = true)
  List<EmployeeEntity> getEmployeeEntitiesWithFilter(@Param("firstname")String firstname,
                                         @Param("lastname")String lastname,
                                         @Param("post")String post,
                                         @Param("sex")String sex,
                                         @Param("entranceDateStart") LocalDate entranceDateStart,
                                         @Param("entranceDateEnd")LocalDate entranceDateEnd,
                                         @Param("exitDateStart") LocalDate exitDateStart,
                                         @Param("exitDateEnd")LocalDate exitDateEnd,
                                         @Param("firstnameOrder")String firstnameOrder,
                                         @Param("lastnameOrder")String lastnameOrder,
                                         @Param("sexOrder")String sexOrder,
                                         @Param("postOrder")String postOrder,
                                         @Param("entranceDateOrder")String entranceDateOrder,
                                         @Param("exitDateOrder")String exitDateOrder,
                                         Pageable pageable);
}
