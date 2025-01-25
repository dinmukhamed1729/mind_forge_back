package din.kz.mind_forge_back.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "difficulties")
public class Difficulty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String level;
}

