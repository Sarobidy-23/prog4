package com.example.demo.service;

import com.example.demo.model.EmployeeEntity;
import com.example.demo.repository.EmployeeRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {
  public final EmployeeRepository repository;

  public void save(EmployeeEntity toSave) {
//        .image(Base64.getEncoder().encodeToString(imageBytes)).build();
    repository.save(toSave);
  }

  public EmployeeEntity findById(String id) {
    Optional<EmployeeEntity> result = repository.findById(id);
    if(result.isPresent()) {
      return result.get();
    } else {
      throw new RuntimeException("No data to display");
    }
  }

  public List<EmployeeEntity> getAll(int page, int pageSize) {
    Pageable pageable = PageRequest.of(page, pageSize);
    return repository.findAll();
  }
  public List<EmployeeEntity> getWithFilter(String firstname,
                                            String lastname,
                                            String sex,
                                            String post,
                                            LocalDate entranceDateStart,
                                            LocalDate entranceDateEnd,
                                            LocalDate exitDateStart,
                                            LocalDate exitDateEnd,
                                            int page, int pageSize) {

      Pageable pageable = PageRequest.of(page, pageSize);
      return repository.getEmployeeEntitiesWithFilter(firstname, lastname, post,sex, entranceDateStart,
          entranceDateEnd, exitDateStart, exitDateEnd, pageable);
  }
}
