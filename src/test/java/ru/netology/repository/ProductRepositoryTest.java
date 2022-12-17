package ru.netology.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

public class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();

    Product book1 = new Book(12, "Book 1", 400, "Синицин");
    Product book2 = new Book(21, "Book 2", 300, "Иванов");
    Product smartphone1 = new Smartphone(23, "iPhone 12", 5_000, "Apple");
    Product smartphone2 = new Smartphone(32, "iPhone 11", 10_000, "Apple");

    @Test
    public void shouldSaveProduct() {

        repository.save(book1);
        repository.save(book2);
        repository.save(smartphone1);
        repository.save(smartphone2);

        Product[] expected = {book1, book2, smartphone1, smartphone2};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByCorrectId() {

        repository.save(book1);
        repository.save(book2);
        repository.save(smartphone1);
        repository.save(smartphone2);
        repository.removeById(12);

        Product[] expected = {book2, smartphone1, smartphone2};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByIncorrectId() {

        repository.save(book1);
        repository.save(book2);
        repository.save(smartphone1);
        repository.save(smartphone2);

        Assertions.assertThrows(NotFoundException.class, () -> repository.removeById(11));
    }

}
