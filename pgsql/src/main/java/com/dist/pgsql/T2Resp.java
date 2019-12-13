package com.dist.pgsql;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface T2Resp extends CrudRepository<T2,Integer> {
    @Query(value = "from T2")
    List<T2> findAll();

    @Query(value = "select * from t2",nativeQuery = true)
    List<T2> findAll2();
}
