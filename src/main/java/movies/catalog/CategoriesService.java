package movies.catalog;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoriesService {

    private final CategoriesRepository catRepo;
    public CategoriesService(CategoriesRepository catRepo){
        this.catRepo = catRepo;
    }

    public List<Categories> getAllCats(){
        return catRepo.findAll();
    }

    public Optional<Categories> getCategory(long id){
        return catRepo.findById(id);
    }

    public void saveCategory(@RequestBody Categories category){
        catRepo.save(category);
    }

    public Optional<Categories> editCategory(Long id, @RequestBody Categories category){
        return catRepo.findById(id).map(newCategory->{return catRepo.save(category);});
    }

    public void deleteCategory(long id){
        catRepo.deleteById(id);
    }

    public void deleteAllCats(){
        catRepo.deleteAll();
    }

}
