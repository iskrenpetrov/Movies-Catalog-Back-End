package movies.catalog;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MoviesService {
    private final MoviesRepository movieRepo;
    public MoviesService(MoviesRepository movieRepo){
        this.movieRepo = movieRepo;
    }

    public List<Movies> getAllMovies(){
        return movieRepo.findAll();
    }

    public Optional<Movies> getMovie(long id){
        return movieRepo.findById(id);
    }

    public Optional<Movies> getMovieByCategoryId(long id, long id2){
        return movieRepo.findByIdAndCategoryId(id, id2);
    }

    public void saveMovie(@RequestBody Movies movie){
        movieRepo.save(movie);
    }

    public Optional<Movies> editMovie(Long id, @RequestBody Movies movie){
        return movieRepo.findById(id).map(newMovie->{return movieRepo.save(movie);});
    }

    public void deleteMovie(Movies movie){
        movieRepo.delete(movie);
    }

    public void deleteAllMovies(){
        movieRepo.deleteAll();
    }

    public void deleteById(Long id) {
        movieRepo.deleteById(id);
    }
}
