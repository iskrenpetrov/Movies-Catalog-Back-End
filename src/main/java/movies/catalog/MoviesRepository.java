package movies.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MoviesRepository extends JpaRepository<Movies, Long> {
    Optional<Movies> findByIdAndCategoryId(Long id, Long categoryId);
}
