package org.atom.stockwell.db.builders;

import org.atom.stockwell.db.classes.LagerProduct;
import org.atom.stockwell.db.classes.Product;

import java.util.Date;

public class LagerProductBuilder{

    LagerProduct currentProduct;

    public LagerProductBuilder startBuild() {
        currentProduct = new LagerProduct();
        return this;
    }

    public LagerProductBuilder setId(String id) {
        currentProduct.setId(id);
        return this;
    }

    public LagerProductBuilder setProduct(Product product) {
        currentProduct.setProduct(product);
        return this;
    }

    public LagerProductBuilder setAmount(int amount) {
        currentProduct.setAmount(amount);
        return this;
    }

    public LagerProductBuilder setDate(Date date) {
        currentProduct.setDate(date);
        return this;
    }

    public LagerProductBuilder setCost(int cost) {
        currentProduct.setCost(cost);
        return this;
    }

    public LagerProduct doneBuild() {
        return currentProduct;
    }
}
