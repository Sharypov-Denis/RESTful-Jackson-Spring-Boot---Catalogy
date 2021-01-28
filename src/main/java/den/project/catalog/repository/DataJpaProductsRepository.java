package den.project.catalog.repository;

import den.project.catalog.model.Products;
import den.project.catalog.util.ValidationUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class DataJpaProductsRepository implements ProductsRepository {
    //private static final Logger log = LoggerFactory.getLogger(DataJpaProductsRepository.class);

    private final CrudProductsRepository repository;

    public DataJpaProductsRepository(CrudProductsRepository crudProductsRepository) {
        this.repository = crudProductsRepository;
    }

    @Override
    @Transactional
    public Products save(Products products) {
        ValidationUtil.checkNewProduct(products);
      //  log.info("save Product");
        return repository.save(products);
    }

    @Override
    @Transactional
    public Products update(Products products) {
     //   log.info("update Product {}", products.getId());
        return repository.save(products);
    }

    @Override
    public Products get(Integer id) {
     //   log.info("get Product {}", id);
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
     //   log.info("delete Product {}", id);
    }

    @Override
    public List<Products> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Products> findProductsByName(String name) {
        return repository.findProductsByName(name);
    }

    @Override
    public Products findOneProductsByName(String name) {
        return repository.findOneProductsByName(name);
    }
}
