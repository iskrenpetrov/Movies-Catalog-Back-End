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

    public void saveMovie(@RequestBody Movies movie){
        movieRepo.save(movie);
    }

    public Optional<Movies> editMovie(Long id, @RequestBody Movies movie){
        return movieRepo.findById(id).map(newMovie->{return movieRepo.save(movie);});
    }

    public void deleteMovie(long id){
        movieRepo.deleteById(id);
    }

    public void deleteAllMovies(){
        movieRepo.deleteAll();
    }
}
