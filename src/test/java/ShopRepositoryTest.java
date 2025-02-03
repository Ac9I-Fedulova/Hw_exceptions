import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    Product product1 = new Product(7, "Кепка", 1_500);
    Product product2 = new Product(15, "Футболка", 2_500);
    ShopRepository repository = new ShopRepository();

    @Test
    public void shouldBeRemovedExistingProduct() {
        repository.add(product1);
        repository.add(product2);

        repository.remove(7); // удалим продукт1

        Product[] expected = new Product[]{product2};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldBeRemovedNonExistentProduct() {
        repository.add(product1);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.remove(15);
        });
    }

    @Test
    public void shouldAddNonDuplicateProduct() {
        repository.add(product1);
        repository.add(product2);

        Product[] expected = new Product[]{product1, product2};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotDuplicateExistentProduct() {
        repository.add(product1);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repository.add(product1);
        });
    }
}
