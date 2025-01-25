package din.kz.mind_forge_back.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "difficulty_id")
    private Difficulty difficulty;

    @ManyToMany
    @JoinTable(name = "task_tags", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TestCase> testCases;

    private int timeLimit;
    private int memoryLimit;

    @Column(columnDefinition = "TEXT")
    private String inputFormat;

    @Column(columnDefinition = "TEXT")
    private String outputFormat;
}

