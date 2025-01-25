package din.kz.mind_forge_back.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    private int executionTime; // В миллисекундах
    private int memoryUsed; // В килобайтах

    @Column(columnDefinition = "TEXT")
    private String code;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    private LocalDateTime submissionTime;
}
