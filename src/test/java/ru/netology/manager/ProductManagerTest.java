package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

public class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);

    Product book1 = new Book(12, "Book 1", 400, "Синицин");
    Product book2 = new Book(21, "Book 2", 300, "Иванов");
    Product smartphone1 = new Smartphone(23, "iPhone 12", 5_000, "Apple");
    Product smartphone2 = new Smartphone(32, "iPhone 11", 10_000, "Apple");

    @Test
    public void shouldAddProduct() {
        manager.add(book1);
        manager.add(smartphone2);

        Product[] expected = {book1, smartphone2};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByNameWhenOneProductFound() {

        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);

        Product[] expected = {smartphone1};
        Product[] actual = manager.searchBy("iPhone 12");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchWhenProductsNotSuit() {

        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);


        Product[] expected = {};
        Product[] actual = manager.searchBy("Samsung");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchWhenFewProductFound() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);

        Product[] expected = {book1, book2};
        Product[] actual = manager.searchBy("Book");

        Assertions.assertArrayEquals(expected, actual);
    }

}
