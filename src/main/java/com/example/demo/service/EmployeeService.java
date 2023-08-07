package com.example.demo.service;

import com.example.demo.model.EmployeeEntity;
import com.example.demo.model.EmployeeFilter;
import com.example.demo.model.PhoneEntity;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PhoneRepository;
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
  private final EmployeeRepository repository;
  private final PhoneRepository phoneRepository;

  public EmployeeEntity save(EmployeeEntity toSave) {
    return repository.save(toSave);
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
