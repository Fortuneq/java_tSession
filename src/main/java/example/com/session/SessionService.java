package example.com.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionRepository repo;

    public List<Session> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public void save(Session session) {
        repo.save(session);
    }

    public Session get(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Session> filterByDate(LocalDateTime date) {
        return repo.findBySessionDateTimeAfter(date);
    }
}
