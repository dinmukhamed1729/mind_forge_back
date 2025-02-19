
package din.kz.mind_forge_back.repository;

import din.kz.mind_forge_back.model.dto.TaskDTO;
import din.kz.mind_forge_back.model.entity.Task;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTitle(String title);

    @Transactional
    @Query("""
                SELECT t FROM Task t 
                LEFT JOIN fetch TestCase tc 
                ON t.id = tc.task.id AND tc.isPublic = true 
                WHERE t.title = :title
            """)
    Optional<Task> findByTitleAndTestCasesIsPublicTrue(@Param("title") String title);




    Optional<TaskDTO> findTaskDTOByTitle(@Param("title") String title);



}
