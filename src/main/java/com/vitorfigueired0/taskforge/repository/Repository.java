package com.vitorfigueired0.taskforge.repository;

import java.util.List;

public interface Repository<U>{

  List<U> findAll();
  U findById(Long id);
  U create(U u);
  U update (U u);
  void delete(Long id);

}
