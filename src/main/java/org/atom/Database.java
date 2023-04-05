package org.atom;

import org.atom.types.*;

import java.util.ArrayList;

public class Database {

    public static Worker currentWorker;

    public static ArrayList<Product> lager = new ArrayList<>();
    public static ArrayList<ProductTemplate> templates = new ArrayList<>();

    public static ArrayList<Transaction> transactions = new ArrayList<>();
    public static ArrayList<Category> categories = new ArrayList<>();

    public static ArrayList<Worker> workers = new ArrayList<>();
    public static ArrayList<Client> clients = new ArrayList<>();

    public static void InitBaseData() {
        Category category1 = new Category(1, "Baustoff");
        Category category2 = new Category(2, "Medikameten");

        categories.add(category1);
        categories.add(category2);

        templates.add(new ProductTemplate(1, "Holz", "0x01", category1));
        templates.add(new ProductTemplate(2, "Stein", "0x02", category1));
        templates.add(new ProductTemplate(3, "Parol", "0x03", category2));

        workers.add(new Worker(1, "çağlar", "cacarekt"));
        workers.add(new Worker(2, "burak", "rugotr"));

        lager.add(new Product(templates.get(0), workers.get(0), 1, 1000, 10));
        lager.add(new Product(templates.get(1), workers.get(0), 2, 2000, 50));
        lager.add(new Product(templates.get(2), workers.get(1), 3, 50000, 10));

        clients.add(new Client(1, "dummy", "5555555555"));
    }

    public static void BuyProduct(ProductTemplate temp, int amount, int unit_cost) {
        Product product = new Product(temp, currentWorker, lager.size() + 1, amount, unit_cost);
        lager.add(product);
        transactions.add(new Transaction(transactions.size(), product, currentWorker,
                amount, unit_cost, TransactionType.PURCHASE));
    }

    public static void SellProduct(Product product, Client client, int amount, int unit_cost) {
        Product inLagerProduct = lager.stream()
                .filter(p -> product.getId() == p.getId())
                .findFirst()
                .orElse(null);
        if (inLagerProduct == null)
            return;
        inLagerProduct.amount -= amount;
        if (inLagerProduct.amount <= 0)
            lager.remove(inLagerProduct);
        transactions.add(new Transaction(transactions.size(), inLagerProduct, currentWorker, client,
                amount, unit_cost, TransactionType.SALE));
    }
}
