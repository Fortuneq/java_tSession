package example.com.session;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String movieName;

    @Getter
    @Setter
    private String studio;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Getter
    @Setter
    private LocalDateTime sessionDateTime;

    @Getter
    @Setter
    private int tickets;

    // Конструктор по умолчанию
    protected Session() {
    }
}
