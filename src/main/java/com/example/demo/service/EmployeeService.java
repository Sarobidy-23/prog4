package com.example.demo.service;

import com.example.demo.model.EmployeeEntity;
import com.example.demo.model.EmployeeFilter;
import com.example.demo.repository.EmployeeRepository;
import io.micrometer.common.util.StringUtils;
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

  public Sort.Direction getSortDirection(String value) {
    Sort.Direction direction = Sort.Direction.ASC;
    if(!StringUtils.isBlank(value)) {
      switch (value.toUpperCase()) {
        case "ASC":
          direction = Sort.Direction.ASC;
          break;
        case "DESC":
          direction = Sort.Direction.DESC;
          break;
        default:
          throw new IllegalArgumentException("Invalid sort direction value: " + value);
      }
    }
    return direction;
  }
  public List<EmployeeEntity> getWithFilter(EmployeeFilter filter,
                                            int page, int pageSize) {

      Pageable pageable = PageRequest.of(
          page,
          pageSize);
      return repository.getEmployeeEntitiesWithFilter(
          filter.getFirstname(),
          filter.getLastname(),
          filter.getPost(),
          filter.getSex(),
          filter.getEntranceDateStart(),
          filter.getEntranceDateEnd(),
          filter.getExitDateStart(),
          filter.getExitDateEnd(),
          filter.getFirstnameOrder(),
          filter.getLastnameOrder(),
          filter.getSexOrder(),
          filter.getPostOrder(),
          filter.getEntranceDateOrder(),
          filter.getExitDateOrder(),
          pageable);
  }
}
