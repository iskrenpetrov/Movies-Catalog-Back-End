package movies.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
}
