package ru.kogut.listener;

import lombok.NoArgsConstructor;
import ru.kogut.enumerations.RolesEnum;
import ru.kogut.model.dao.CategoryEntity;
import ru.kogut.model.dao.ProductEntity;
import ru.kogut.model.dao.RoleEntity;
import ru.kogut.model.dao.UserEntity;
import ru.kogut.repository.ProductRepository;
import ru.kogut.service.CategoryService;
import ru.kogut.service.ProductService;
import ru.kogut.service.interfaces.CategoryInt;
import ru.kogut.service.interfaces.ProductInt;
import ru.kogut.service.interfaces.RoleInt;
import ru.kogut.service.interfaces.UserInt;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author S.Kogut on 21.01.2020
 */

@WebListener
@NoArgsConstructor
public class AppListener implements ServletContextListener {

    @EJB
    private CategoryInt categoryService;

    @EJB
    private ProductInt productService;

    @EJB
    private UserInt userRepository;

    @EJB
    private RoleInt roleRepository;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        initProducts();
        initUsers();
    }

    public void initUsers() {
        List<UserEntity> temp = userRepository.findByName("admin");
        if (temp == null || temp.size() == 0) {
            UserEntity userAdmin = new UserEntity();
            userAdmin.setLogin("admin");
            userAdmin.setPassword("admin".toCharArray());
            RoleEntity roleAdmin = new RoleEntity(Stream.of(userAdmin).collect(Collectors.toList()),RolesEnum.ADMIN);
            userAdmin.setRoles(Stream.of(roleAdmin).collect(Collectors.toList()));
            userRepository.saveOrUpdate(userAdmin);
        }
        temp = userRepository.findByName("user");
        if (temp == null || temp.size() == 0) {
            UserEntity user = new UserEntity();
            user.setLogin("user");
            user.setPassword("user".toCharArray());
            RoleEntity roleUser = new RoleEntity(Stream.of(user).collect(Collectors.toList()),RolesEnum.USER);
            user.setRoles(Stream.of(roleUser).collect(Collectors.toList()));
            userRepository.saveOrUpdate(user);
        }
    }

    public void initProducts() {
        //Create category.
        CategoryEntity categoryDAO;
        categoryDAO = categoryService.findById("1");
        if (categoryDAO == null) {
            categoryDAO = new CategoryEntity();
            categoryDAO.setId("1");
            categoryDAO.setTitle("Мобильные телефоны");
            categoryDAO.setDescription("В этом разделе расположены мобильные телефоны");
            categoryService.saveOrUpdate(categoryDAO);
        }

        //Create products.
        ProductEntity product1;
        if (productService.findById("1") == null) {
            product1 = new ProductEntity();
            product1.setId("1");
            product1.setCategory(categoryDAO);
            product1.setShortDescription("<ul type=\"disc\">" +
                    "<li>смартфон с Android 9.0</li>" +
                    "<li>экран 6.4\", разрешение 2340x1080</li>" +
                    "<li>память 64 Гб, слот для карты памяти</li>" +
                    "</ul>");
            product1.setTitle("Смартфон Galaxy A50 64GB");
            product1.setFullDescription("<ul type=\"disc\">" +
                    "<li>смартфон с Android 9.0</li>" +
                    "<li>поддержка двух SIM-карт</li>" +
                    "<li>экран 6.4\", разрешение 2340x1080</li>" +
                    "<li>три камеры 25 МП/8 МП/5 МП, автофокус</li>" +
                    "<li>память 64 Гб, слот для карты памяти</li>" +
                    "<li>3G, 4G LTE, LTE-A, Wi-Fi, Bluetooth, NFC, GPS, ГЛОНАСС</li>" +
                    "<li>объем оперативной памяти 4 Гб</li>" +
                    "<li>аккумулятор 4000 мА⋅ч</li>" +
                    "<li>вес 166 г, ШxВxТ 74.70x158.50x7.70 мм</li>" +
                    "</ul>");
            product1.setPathTitlePicture("images/mobile/galaxyA50.jpg");
            product1.setPrice(new BigDecimal(15890));
            productService.saveOrUpdate(product1);
        }

        ProductEntity product2;
        if (productService.findById("2") == null) {
            product2 = new ProductEntity();
            product2.setCategory(categoryDAO);
            product2.setId("2");
            product2.setShortDescription("<ul type=\"disc\">" +
                    "<li>смартфон с iOS 11</li>" +
                    "<li>экран 4.7\", разрешение 1334x750</li>" +
                    "<li>память 64 Гб, без слота для карт памяти</li>" +
                    "</ul>");
            product2.setTitle("Смартфон Apple iPhone 8 64GB");
            product2.setFullDescription("<ul type=\"disc\">" +
                    "<li>смартфон с iOS 11</li>" +
                    "<li>экран 4.7\", разрешение 1334x750</li>" +
                    "<li>камера 12 МП, автофокус</li>" +
                    "<li>память 64 Гб, без слота для карт памяти</li>" +
                    "<li>3G, 4G LTE, LTE-A, Wi-Fi, Bluetooth, NFC, GPS, ГЛОНАСС</li>" +
                    "<li>вес 148 г, ШxВxТ 67.30x138.40x7.30 мм</li>" +
                    "</ul>");
            product2.setPathTitlePicture("images/mobile/iPhone8.jpg");
            product2.setPrice(new BigDecimal(31750));
            productService.saveOrUpdate(product2);
        }

        ProductEntity product3;
        if (productService.findById("3") == null) {
            product3 = new ProductEntity();
            product3.setCategory(categoryDAO);
            product3.setId("3");
            product3.setShortDescription("<ul type=\"disc\">" +
                    "<li>смартфон с iOS 13</li>" +
                    "<li>экран 5.8\", разрешение 2436x1125</li>" +
                    "<li>память 64 Гб, без слота для карт памяти</li>" +
                    "</ul>");
            product3.setTitle("Смартфон Apple iPhone 11 Pro 64GB");
            product3.setFullDescription("<ul type=\"disc\">" +
                    "<li>смартфон с iOS 13</li>" +
                    "<li>экран 5.8\", разрешение 2436x1125</li>" +
                    "<li>три камеры 12 МП/12 МП/12 МП, автофокус</li>" +
                    "<li>память 64 Гб, без слота для карт памяти</li>" +
                    "<li>3G, 4G LTE, LTE-A, Wi-Fi, Bluetooth, NFC, GPS, ГЛОНАСС</li>" +
                    "<li>вес 188 г, ШxВxТ 71.40x144x8.10 мм</li>" +
                    "</ul>");
            product3.setPathTitlePicture("images/mobile/iPhone11.jpg");
            product3.setPrice(new BigDecimal(72900));
            productService.saveOrUpdate(product3);
        }

        ProductEntity product4;
        if (productService.findById("4") == null) {
            product4 = new ProductEntity();
            product4.setCategory(categoryDAO);
            product4.setId("4");
            product4.setShortDescription("<ul type=\"disc\">" +
                    "<li>смартфон с iOS 12</li>" +
                    "<li>экран 6.1\", разрешение 1792x828</li>" +
                    "<li>память 64 Гб, без слота для карт памяти</li>" +
                    "</ul>");
            product4.setTitle("Смартфон Apple iPhone Xr 64GB");
            product4.setFullDescription("<ul type=\"disc\">" +
                    "<li>смартфон с iOS 12</li>" +
                    "<li>поддержка двух SIM-карт (nano SIM+eSIM)</li>" +
                    "<li>экран 6.1\", разрешение 1792x828</li>" +
                    "<li>камера 12 МП, автофокус</li>" +
                    "<li>объем оперативной памяти 3 Гб</li>" +
                    "<li>память 64 Гб, без слота для карт памяти</li>" +
                    "<li>3G, 4G LTE, LTE-A, Wi-Fi, Bluetooth, NFC, GPS, ГЛОНАСС</li>" +
                    "<li>вес 194 г, ШxВxТ 75.70x150.90x8.30 мм</li>" +
                    "</ul>");
            product4.setPathTitlePicture("images/mobile/iPhoneXr.jpg");
            product4.setPrice(new BigDecimal(42050));
            productService.saveOrUpdate(product4);
        }

        ProductEntity product5;
        if (productService.findById("5") == null) {
            product5 = new ProductEntity();
            product5.setCategory(categoryDAO);
            product5.setId("5");
            product5.setShortDescription("<ul type=\"disc\">" +
                    "<li>смартфон с iOS 12</li>" +
                    "<li>экран 5.8\", разрешение 2436x1125</li>" +
                    "<li>память 64 Гб, без слота для карт памяти</li>" +
                    "</ul>");
            product5.setTitle("Смартфон Apple iPhone Xs 64GB");
            product5.setFullDescription("<ul type=\"disc\">" +
                    "<li>смартфон с iOS 12</li>" +
                    "<li>поддержка двух SIM-карт (nano SIM+eSIM)</li>" +
                    "<li>экран 5.8\", разрешение 2436x1125</li>" +
                    "<li>двойная камера 12 МП/12 МП, автофокус</li>" +
                    "<li>объем оперативной памяти 4 Гб</li>" +
                    "<li>память 64 Гб, без слота для карт памяти</li>" +
                    "<li>3G, 4G LTE, LTE-A, Wi-Fi, Bluetooth, NFC, GPS, ГЛОНАСС</li>" +
                    "<li>вес 177 г, ШxВxТ 70.90x143.60x7.70 мм</li>" +
                    "</ul>");
            product5.setPathTitlePicture("images/mobile/iPhoneXs.jpg");
            product5.setPrice(new BigDecimal(47780));
            productService.saveOrUpdate(product5);
        }

        ProductEntity product6;
        if (productService.findById("6") == null) {
            product6 = new ProductEntity();
            product6.setCategory(categoryDAO);
            product6.setId("6");
            product6.setShortDescription("<ul type=\"disc\">" +
                    "<li>смартфон на платформе Android</li>" +
                    "<li>экран 6.39\", разрешение 2340x1080</li>" +
                    "<li>память 128 Гб, слот для карты памяти</li>" +
                    "</ul>");
            product6.setTitle("Смартфон Xiaomi Mi 9 Lite 6/128GB");
            product6.setFullDescription("<ul type=\"disc\">" +
                    "<li>смартфон на платформе Android</li>" +
                    "<li>поддержка двух SIM-карт</li>" +
                    "<li>экран 6.39\", разрешение 2340x1080</li>" +
                    "<li>три камеры 48 МП/8 МП/2 МП, автофокус</li>" +
                    "<li>объем оперативной памяти 6 Гб</li>" +
                    "<li>память 128 Гб, слот для карты памяти</li>" +
                    "<li>аккумулятор 4030 мА⋅ч</li>" +
                    "<li>3G, 4G LTE, Wi-Fi, Bluetooth, NFC, GPS, ГЛОНАСС</li>" +
                    "<li>вес 179 г, ШxВxТ 74.50x156.80x8.67 мм</li>" +
                    "</ul>");
            product6.setPathTitlePicture("images/mobile/mi9Lite6.jpg");
            product6.setPrice(new BigDecimal(19990));
            productService.saveOrUpdate(product6);
        }

        ProductEntity product7;
        if (productService.findById("7") == null) {
            product7 = new ProductEntity();
            product7.setCategory(categoryDAO);
            product7.setId("7");
            product7.setShortDescription("<ul type=\"disc\">" +
                    "<li>смартфон на платформе Android</li>" +
                    "<li>экран 6.09\", разрешение 1560x720</li>" +
                    "<li>память 64 Гб, слот для карты памяти</li>" +
                    "</ul>");
            product7.setTitle("Смартфон Xiaomi Mi A3 4/64GB Android One");
            product7.setFullDescription("<ul type=\"disc\">" +
                    "<li>смартфон на платформе Android</li>" +
                    "<li>поддержка двух SIM-карт</li>" +
                    "<li>экран 6.09\", разрешение 1560x720</li>" +
                    "<li>три камеры 48 МП/8 МП/2 МП, автофокус</li>" +
                    "<li>объем оперативной памяти 4 Гб</li>" +
                    "<li>память 64 Гб, слот для карты памяти</li>" +
                    "<li>аккумулятор 4030 мА⋅ч</li>" +
                    "<li>3G, 4G LTE, Wi-Fi, Bluetooth, GPS, ГЛОНАСС</li>" +
                    "<li>вес 174 г, ШxВxТ 71.85x153.48x8.48 мм</li>" +
                    "</ul>");
            product7.setPathTitlePicture("images/mobile/miA34.jpg");
            product7.setPrice(new BigDecimal(15990));
            productService.saveOrUpdate(product7);
        }

        ProductEntity product8;
        if (productService.findById("8") == null) {
            product8 = new ProductEntity();
            product8.setCategory(categoryDAO);
            product8.setId("8");
            product8.setShortDescription("<ul type=\"disc\">" +
                    "<li>смартфон на платформе Android</li>" +
                    "<li>экран 6.47\", разрешение 2340x1080</li>" +
                    "<li>память 128 Гб, без слота для карт памяти</li>" +
                    "</ul>");
            product8.setTitle("Смартфон Xiaomi Mi Note 10 6/128GB");
            product8.setFullDescription("<ul type=\"disc\">" +
                    "<li>смартфон на платформе Android</li>" +
                    "<li>поддержка двух SIM-карт</li>" +
                    "<li>экран 6.47\", разрешение 2340x1080</li>" +
                    "<li>пять камер 108 МП/12 МП/20 МП/5 МП, автофокус</li>" +
                    "<li>объем оперативной памяти 6 Гб</li>" +
                    "<li>память 128 Гб, без слота для карт памяти</li>" +
                    "<li>аккумулятор 5260 мА⋅ч</li>" +
                    "<li>3G, 4G LTE, Wi-Fi, Bluetooth, NFC, GPS, ГЛОНАСС</li>" +
                    "<li>вес 208 г, ШxВxТ 74.20x157.80x9.67 мм</li>" +
                    "</ul>");
            product8.setPathTitlePicture("images/mobile/miNote10.png");
            product8.setPrice(new BigDecimal(36990));
            productService.saveOrUpdate(product8);
        }

        ProductEntity product9;
        if (productService.findById("9") == null) {
            product9 = new ProductEntity();
            product9.setCategory(categoryDAO);
            product9.setId("9");
            product9.setShortDescription("<ul type=\"disc\">" +
                    "<li>смартфон на платформе Android</li>" +
                    "<li>экран 6.53\", разрешение 2340x1080</li>" +
                    "<li>память 64 Гб, слот для карты памяти</li>" +
                    "</ul>");
            product9.setTitle("Смартфон Xiaomi Redmi Note 8 Pro 6/64GB");
            product9.setFullDescription("<ul type=\"disc\">" +
                    "<li>смартфон на платформе Android</li>" +
                    "<li>поддержка двух SIM-карт</li>" +
                    "<li>экран 6.53\", разрешение 2340x1080</li>" +
                    "<li>четыре камеры 64 МП/8 МП/2 МП/2 МП, автофокус</li>" +
                    "<li>объем оперативной памяти 6 Гб</li>" +
                    "<li>память 64 Гб, слот для карты памяти</li>" +
                    "<li>аккумулятор 4500 мА⋅ч</li>" +
                    "<li>3G, 4G LTE, Wi-Fi, Bluetooth, NFC, GPS, ГЛОНАСС</li>" +
                    "<li>вес 200 г, ШxВxТ 76.40x161.35x8.79 мм</li>" +
                    "</ul>");
            product9.setPathTitlePicture("images/mobile/redmi8.png");
            product9.setPrice(new BigDecimal(17990));
            productService.saveOrUpdate(product9);
        }

        ProductEntity product10;
        if (productService.findById("10") == null) {
            product10 = new ProductEntity();
            product10.setCategory(categoryDAO);
            product10.setId("10");
            product10.setShortDescription("<ul type=\"disc\">" +
                    "<li>смартфон на платформе Android</li>" +
                    "<li>экран 6.3\", разрешение 2340x1080</li>" +
                    "<li>память 64 Гб, слот для карты памяти</li>" +
                    "</ul>");
            product10.setTitle("Смартфон Xiaomi Redmi Note 8T 4/64GB");
            product10.setFullDescription("<ul type=\"disc\">" +
                    "<li>смартфон на платформе Android</li>" +
                    "<li>поддержка двух SIM-карт</li>" +
                    "<li>экран 6.3\", разрешение 2340x1080</li>" +
                    "<li>четыре камеры 48 МП/8 МП/2 МП/2 МП, автофокус</li>" +
                    "<li>объем оперативной памяти 4 Гб</li>" +
                    "<li>память 64 Гб, слот для карты памяти</li>" +
                    "<li>аккумулятор 4000 мА⋅ч</li>" +
                    "<li>33G, 4G LTE, Wi-Fi, Bluetooth, NFC, GPS, ГЛОНАСС</li>" +
                    "<li>вес 200 г, ШxВxТ 75.40x161.15x8.60 мм</li>" +
                    "</ul>");
            product10.setPathTitlePicture("images/mobile/redmiNote.jpg");
            product10.setPrice(new BigDecimal(17990));
            productService.saveOrUpdate(product10);
        }
    }
}
