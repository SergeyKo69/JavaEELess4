package ru.kogut.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * @author S.Kogut on 21.01.2020
 */

@Named
@ApplicationScoped
public class MainController {

    public String doBasket() {
        return "basket.xhtml";
    }

    public String doAdministrativeCategory() {
        return "categoryList.xhtml";
    }

    public String doAdministrativeProduct() {
        return "productList.xhtml";
    }
}
