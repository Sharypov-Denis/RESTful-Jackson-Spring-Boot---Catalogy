package den.project.catalog.repository;


import den.project.catalog.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CrudProductsRepository extends JpaRepository<Products, Integer> {
    @Query("SELECT p FROM Products p WHERE p.name=:nameProducts")
    List<Products> findProductsByName(@Param("nameProducts") String nameProducts);

    @Query("SELECT p FROM Products p WHERE p.name=:nameProducts")
    Products findOneProductsByName(@Param("nameProducts") String nameProducts);
}
