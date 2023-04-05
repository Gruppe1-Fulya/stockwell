package org.atom.types;

public abstract class ProductTemplate {
    private int id;

    public String name;
    public String barcode;
    public Category category;

    public ProductTemplate(ProductTemplate template) {
        name = template.name;
        barcode = template.barcode;
        category = template.category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
