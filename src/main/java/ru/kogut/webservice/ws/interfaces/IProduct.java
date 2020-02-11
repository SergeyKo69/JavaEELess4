package ru.kogut.webservice.ws.interfaces;

import ru.kogut.model.dto.ProductDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * @author S.Kogut on 10.02.2020
 */

@WebService
public interface IProduct {

    @WebMethod
    void add(ProductDTO product);

    @WebMethod
    void delete(ProductDTO product);

    @WebMethod
    ProductDTO findById(String id);

    @WebMethod
    ProductDTO addCategory(String idProduct, String idCategory);

    @WebMethod
    List<ProductDTO> findByName(String name);

    @WebMethod
    List<ProductDTO> findAll();

    @WebMethod
    List<ProductDTO> findByIdCategory(String idCategory);

}
