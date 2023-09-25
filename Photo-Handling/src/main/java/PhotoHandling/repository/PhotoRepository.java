package PhotoHandling.repository;

import PhotoHandling.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo,Integer> {
    Optional<Photo> findByName(String fileName);
}
