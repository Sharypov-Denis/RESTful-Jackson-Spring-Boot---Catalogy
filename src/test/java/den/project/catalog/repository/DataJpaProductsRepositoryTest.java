package den.project.catalog.repository;

import den.project.catalog.model.Products;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/InitDB_PostgreSQL.sql", config = @SqlConfig(encoding = "UTF-8"))
@SpringBootTest
public class DataJpaProductsRepositoryTest {

    public static final int START_SEQ = 1;

    public final static Integer ID_1 = 1;
    public final static Products PRODUCTS_1 = new Products(1, "Молоко", "описание продукта");
    public final static Products PRODUCTS_1_UPDATE = new Products(ID_1, "МолокоTEST", "описание продуктаTEST");

    public final static Integer ID_2 = 2;
    public final static Products PRODUCTS_2 = new Products(2, "Творог", "описание продукта");
    public final static Products PRODUCTS_2_UPDATE = new Products(ID_2, "ТворогTEST", "описание продуктаTEST");

    public final static Integer ID_3 = 3;
    public final static Products PRODUCTS_3 = new Products(3, "Сливки", "описание продукта");
    public final static Products PRODUCTS_3_UPDATE = new Products(ID_3, "СливкиTEST", "описание продуктаTEST");


    public final static Integer ID_11 = 11;
    public final static Products PRODUCTS_11 = new Products(null, "TestName11", "TestDescription11");
    public final static Products PRODUCTS_11_UPDATE = new Products(ID_11, "TestName11Update", "TestDescription11Update");
    public final static List<Products> newList = null;


    @Autowired
    DataJpaProductsRepository repository;
    @Autowired
    CrudProductsRepository crudProductsRepository;

    @Before
    @Sql(scripts = "classpath:db/InitDB_PostgreSQL.sql", config = @SqlConfig(encoding = "UTF-8"))
    public void deleteALL() {
    }

    @Test
    public void save() {
        repository.save(PRODUCTS_11);
        assertThat(PRODUCTS_11).isEqualTo(repository.get(ID_11));
    }

    @Test
    public void update() {
        repository.update(PRODUCTS_1_UPDATE);
        assertThat(PRODUCTS_1_UPDATE).isEqualTo(repository.get(ID_1));
    }

    @Test
    public void get() {
        assertThat(PRODUCTS_2).isEqualTo(repository.get(ID_2));
    }

    @Test
    public void delete() {
        repository.delete(ID_3);
        assertThrows(EmptyResultDataAccessException.class, () -> repository.delete(ID_3));
    }

    @Test
    public void getAll() {
    }

    @Test
    public void getProductNull() {
        Assert.assertNull(repository.get(12));
    }

    @Test
    public void getListProductNull() {
        Assert.assertNotNull(repository.getAll());
    }

    /*

    @Test
    public void findProductsByName() {
        assertThat(PRODUCTS_2).isEqualTo(repository.findProductsByName("Творог"));
    }

    @Test
    public void findOneProductsByName() {
        assertThat(repository.findProductsByName("Творог")).isEqualTo(PRODUCTS_2);
    }


    @Test
    public void whenSavingProduct_thenCorrect() {
        crudProductsRepository.save(new Products("Bob", "bob@domain.com"));
        Products product = crudProductsRepository.findById(1).orElseGet(()
                -> new Products("john", "john@domain.com"));
        assertThat(product.getName()).isEqualTo("Bob");

        crudProductsRepository.deleteAll();
    }

 */
/*
    @Test
    public void whenFindingProductById_thenCorrect() {
        repository.save(new Products("John", "john@domain.com"));
        assertThat(repository.findById(1)).isInstanceOf(Optional.class);

        repository.deleteAll();
    }

    @Test
    public void whenFindingAllProducts_thenCorrect() {
        repository.save(new Products(null, "John", "john@domain.com"));
        repository.save(new Products(null, "Julie", "julie@domain.com"));
        assertThat(repository.findAll()).isInstanceOf(List.class);

        repository.deleteAll();
    }

    @Test
    public void deleteNotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> repository.deleteById(1));
    }
    /*
    @Test
    public void delete() {
        crudProductsRepository.save(new Products(null, "John", "john@domain.com"));
        assertThrows(EmptyResultDataAccessException.class, () -> crudProductsRepository.getOne(12));

    }
     */


}