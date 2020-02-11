package ru.kogut;

import ru.kogut.webservice.ws.implement.IProduct;
import ru.kogut.webservice.ws.implement.ProductWsServiceService;
import ru.kogut.webservice.ws.interfaces.ProductDTO;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author S.Kogut on 11.02.2020
 */
public class Main {
    private static ProductWsServiceService service = new ProductWsServiceService();

    public static void main(String[] args) throws MalformedURLException {
        IProduct port = service.getProductWsServicePort();
        System.out.println("Начало создания продукта");

        ProductDTO testProduct = new ProductDTO();
        testProduct.setId(UUID.randomUUID().toString());
        testProduct.setTitle("Test title");
        testProduct.setFullDescription("Test full description");
        testProduct.setPrice(new BigDecimal(1000));
        port.add(testProduct);


        ProductDTO temp = port.findById(testProduct.getId());
        if (temp != null) {
            System.out.println(temp.getTitle());
        }
        System.out.println("Конец создания продукта");

        System.out.println("Начало добавления категории");
        port.addCategory(testProduct.getId(), "1");
        temp = port.findById(testProduct.getId());
        if (temp != null) {
            System.out.println(temp.getTitle() + " " + temp.getCategory());
        }
        System.out.println("Конец добавления категории");

        System.out.println("Начало поиска по имени");
        List<ProductDTO> productList = port.findByName(testProduct.getTitle());
        if (productList != null) {
            productList.forEach(p->{
                System.out.println("Поиск по имени: " + p.getTitle());
            });
        }
        System.out.println("Конец поиска по имени");

        System.out.println("Вывод всех товаров");
        productList = port.findAll();
        productList.forEach(p->{
            System.out.println(p.getTitle());
        });
        System.out.println("Конец вывода всех товаров");

        System.out.println("Поиск по категории");
        productList = port.findByIdCategory("1");
        productList.forEach(p->{
            System.out.println(p.getTitle());
        });
        System.out.println("Конец поиска по категории");

        System.out.println("Удаление");
        String id = testProduct.getId();
        temp = port.findById(testProduct.getId());
        port.delete(temp);

        temp = port.findById(id);
        if (temp == null) {
            System.out.println("OK");
        }
    }
}
