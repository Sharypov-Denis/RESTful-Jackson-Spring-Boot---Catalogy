package den.project.catalog.util;

import den.project.catalog.model.Products;
import den.project.catalog.repository.ProductsRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidation implements Validator {

    private final ProductsRepository repository;

    public ProductValidation(ProductsRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Products.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Products newProduct = (Products) target;
        String name = newProduct.getName();
        if (repository.findOneProductsByName(name) != null) {
            errors.rejectValue(
                    "name", "","This name is already in use"
            );
        }
    }
}
