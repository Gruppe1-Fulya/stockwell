package org.atom.types;

public class ProductTemplate {
    private int id;

    public String name;
    public String barcode;
    public Category category;

    public ProductTemplate(int id, String name, String barcode, Category category) {
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.category = category;
    }

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
