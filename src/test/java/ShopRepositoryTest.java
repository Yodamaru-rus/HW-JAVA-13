import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    ShopRepository manager = new ShopRepository();
    ShopRepository managerThree = new ShopRepository();
    ShopRepository managerTwo = new ShopRepository();
    Product item1 = new Product(1, "Апельсин", 200);
    Product item2 = new Product(2, "Фарш", 430);
    Product item3 = new Product(3, "Хлеб", 99);
    Product item4 = new Product(4, "Вода", 50);
    Product item5 = new Product(5, "Кофе", 3289);
    Product item6 = new Product(6, "Йогурт", 78);
    Product item7 = new Product(7, "Торт", 340);
    Product item8 = new Product(8, "Балык", 780);

    @BeforeEach
    public void setup() {
        manager.add(item1);
        manager.add(item2);
        manager.add(item3);
        manager.add(item4);
        manager.add(item5);
        manager.add(item6);
        manager.add(item7);
        managerThree.add(item3);
        managerThree.add(item4);
        managerThree.add(item5);
        managerTwo.add(item1);
        managerTwo.add(item6);


    }

    @Test
    public void AddProductSuccessfully() {
        Product[] expected = {item1, item2, item3, item4, item5, item6, item7, item8};

        manager.add(item8);
        Product[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void AddError() {

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            manager.add(item2);
        });
    }

    @Test
    public void findElement() {
        Product[] expected = {item1, item2, item3, item4, item5, item6, item7};

        Product[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findElementThree() {
        Product[] expected = {item3, item4, item5};

        Product[] actual = managerThree.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findByIdSuccessfully() {
        Product expected = item3;

        Product actual = manager.findById(3);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findById() {
        Product expected = null;

        Product actual = managerThree.findById(50);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void removeSuccessfully() {

        Product[] expected = {item6};

        managerTwo.remove(1);
        Product[] actual = managerTwo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeError() {

        Assertions.assertThrows(NotFoundException.class, () -> {
            managerThree.remove(47);
        });
    }

}
