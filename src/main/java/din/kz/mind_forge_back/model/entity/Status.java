package din.kz.mind_forge_back.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "statuses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Например: Accepted, Wrong Answer, Runtime Error
}
