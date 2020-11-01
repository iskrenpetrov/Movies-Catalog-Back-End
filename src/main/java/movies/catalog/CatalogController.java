package movies.catalog;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000, , maxAge = 3600")
@RestController
public class CatalogController {
    private final CategoriesService catServ;
    private final MoviesService moviesServ;
    private final MoviesRepository moviesRepo;
    private final CategoriesRepository catRepo;
    public CatalogController(CategoriesService catServ, MoviesService moviesServ, MoviesRepository moviesRepo, CategoriesRepository catRepo){
        this.catServ = catServ;
        this.moviesServ = moviesServ;
        this.moviesRepo = moviesRepo;
        this.catRepo = catRepo;
    }

    //Categories controller starts here

    @GetMapping("/")
    public List<Categories> listAll(){
        return catServ.getAllCats();
    }
    @GetMapping("/{id}")
    public Optional<Categories> getCategory(@PathVariable Long id){
        return catServ.getCategory(id);
    }
    @PostMapping("/create-category")
    public void createCategory(@RequestBody Categories category){
        catServ.saveCategory(category);
    }
    @PutMapping("/{id}/update")
    public void editCategory(@RequestBody Categories category, @PathVariable Long id){
        catServ.editCategory(id,category);
    }
    @DeleteMapping("/delete")
    public void deleteCategories(){
        catServ.deleteAllCats();
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Long id){
        catServ.deleteCategory(id);
    }

    //Categories controller ends here

    //Movies controller starts here

    @GetMapping("/{id}/movies")
    public Optional<List<Movies>> listAllMovies(@PathVariable Long id){
        Optional<Categories> category = catServ.getCategory(id);
        return category.map(Categories::getMovies);

    }
    @GetMapping("/{id}/movies/{id2}/")
    public Optional<Optional<Movies>> getMovie(@PathVariable Long id, @PathVariable Long id2){
        return catServ.getCategory(id).map(getMovie->{
            return moviesServ.getMovie(id2);
        });
    }
    @PostMapping("/{id}/movies/add-movie")
    public void addMovie(@PathVariable long id,@RequestBody Movies movie){
         catServ.getCategory(id).map(saveMovie->{
            moviesServ.saveMovie(movie);
            return null;
        });
    }
//@PostMapping("/{id}/movies/add-movie")
//public void addMovie(@PathVariable long id,@RequestBody Movies movie){
//        catRepo.findById(id).map((category->{
//        category.getMovies().add(movie);
//        catRepo.saveAndFlush(category);
//            return null;
//        }));
//}
    @PutMapping("/{id}/movies/{id2}/update")
    public void editMovie(@PathVariable Long id, @RequestBody Movies movie, @PathVariable Long id2){
        catServ.getCategory(id).map(editMovie->{
            moviesServ.editMovie(id2,movie);
            return null;
        });
    }
    @DeleteMapping("/{id}/delete")
    public void deleteCategories(@PathVariable Long id){
        catServ.getCategory(id).map(deleteMovies->{
            moviesServ.deleteAllMovies();
            return null;
        });
    }
    @DeleteMapping("/{id}/delete/{id2}")
    public void deleteCategory(@PathVariable Long id, @PathVariable Long id2){
        catServ.getCategory(id).map(deleteMovie->{
            moviesServ.deleteMovie(id2);
            return null;
        });
    }

    //Movies controller ends here

}
