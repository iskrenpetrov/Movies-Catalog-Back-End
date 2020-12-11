package movies.catalog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000, , maxAge = 3600")
@RestController
public class CatalogController {
    private final CategoriesService catServ;
    private final MoviesService moviesServ;
    public CatalogController(CategoriesService catServ, MoviesService moviesServ){
        this.catServ = catServ;
        this.moviesServ = moviesServ;
    }

    //Categories controller starts here

    @GetMapping("/categories")
    public List<Categories> listAll(){
        return catServ.getAllCats();
    }
    @GetMapping("/categories/{id}")
    public Optional<Categories> getCategory(@PathVariable Long id){
        return catServ.getCategory(id);
    }
    @PostMapping("/categories")
    public void createCategory(@RequestBody Categories category){
        catServ.saveCategory(category);
    }
    @PutMapping("/categories/{id}")
    public void editCategory(@RequestBody Categories category, @PathVariable Long id) {
        catServ.getCategory(id).map(categoryM -> {
            categoryM.setName(category.getName());
            categoryM.setId(category.getId());
            catServ.saveCategory(category);
            return null;
        });
    }
    @DeleteMapping("/categories")
    public void deleteCategories(){
        catServ.deleteAllCats();
    }
    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable Long id){
        catServ.deleteCategory(id);
    }

    //Categories controller ends here

    //Movies controller starts here

    @GetMapping("/categories/{id}/movies")
    public Optional<List<Movies>> listAllMovies(@PathVariable Long id){
        Optional<Categories> category = catServ.getCategory(id);
        return category.map(Categories::getMovies);
    }
    @GetMapping("/categories/{id}/movies/{id2}")
    public Optional<Optional<Movies>> getMovie(@PathVariable Long id, @PathVariable Long id2){
        return catServ.getCategory(id).map(category->{
            return moviesServ.getMovie(id2);
        });
    }
    @PostMapping("/categories/{id}/movies")
    public void addMovie(@PathVariable long id,@RequestBody Movies movie){
        catServ.getCategory(id).map(category -> {
        movie.setCategory(category);
        moviesServ.saveMovie(movie);
            return null;
        });
}
    @PutMapping("/categories/{id}/movies/{id2}")
    public void editMovie(@PathVariable Long id, @RequestBody Movies movie, @PathVariable Long id2){
        moviesServ.getMovie(id2).map(mov -> {
            mov.setName(movie.getName());
            mov.setDescription(movie.getDescription());
            moviesServ.saveMovie(mov);
            return null;
        });
    }
    @DeleteMapping("categories/{id}/movies")
    public void deleteCategories(@PathVariable Long id){
        catServ.getCategory(id).map(deleteMovies->{
            moviesServ.deleteAllMovies();
            return null;
        });
    }
    @DeleteMapping("/categories/{id}/movies/{id2}")
    public void deleteCategory(@PathVariable Long id, @PathVariable Long id2) {
        moviesServ.getMovieByCategoryId(id2,id).map(mov->{
            moviesServ.deleteMovie(mov);
            return null;
        });
    }

    //Movies controller ends here

}
