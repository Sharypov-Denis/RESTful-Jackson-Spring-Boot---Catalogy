package den.project.catalog.util;

import den.project.catalog.model.Products;

public class ValidationUtil {

    public static void checkNewProduct(Products products) {
        if (!products.isNew()) {
            throw new RuntimeException(products + " must be new (id=null)");
        }
    }
}
