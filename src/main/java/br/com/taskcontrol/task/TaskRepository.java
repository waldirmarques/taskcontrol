package br.com.taskcontrol.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByTaskStatusOrderByPriorityLevelDesc(TaskStatus pending);

    List<Task> findAllByOrderByTaskStatusAsc();

    @Query("SELECT t FROM task t WHERE t.taskStatus = 0 AND createDate <=:date")
    List<Task> findAllByTaskStatusAndCreateDate(@Param("date") LocalDateTime date);

    Optional<Task> findByTitle(String title);
}
