package example.com.session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query("SELECT s FROM Session s WHERE CONCAT(s.movieName, s.studio, s.sessionDateTime) LIKE %?1%")
    List<Session> search(String keyword);

    List<Session> findBySessionDateTimeAfter(LocalDateTime date);
}
