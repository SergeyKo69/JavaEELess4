package ru.kogut.controller;

import ru.kogut.log.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.interceptor.Interceptors;

/**
 * @author S.Kogut on 21.01.2020
 */

@Named
@ApplicationScoped
public class MainController {

    @Interceptors({Logger.class})
    public String doBasket() {
        return "basket.xhtml";
    }

    @Interceptors({Logger.class})
    public String doAdministrativeCategory() {
        return "categoryList.xhtml";
    }

    @Interceptors({Logger.class})
    public String doAdministrativeProduct() {
        return "productList.xhtml";
    }
}
