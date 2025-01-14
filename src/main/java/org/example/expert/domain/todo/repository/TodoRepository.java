package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoQueryRepository {

    @Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user u ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

    @Query("SELECT t FROM Todo t WHERE t.weather = :weather")
    Page<Todo> findByWeather(@Param("weather") String weather, Pageable pageable);

    @Query("SELECT t FROM Todo t WHERE t.createdAt BETWEEN :startDate AND :endDate")
    Page<Todo> findByDateRange(@Param("startDate") LocalDateTime startDate,
                               @Param("endDate") LocalDateTime endDate, Pageable pageable);
}
