package com.portable.app.interfaces;

import com.portable.app.entity.Sale;
import java.util.List;

public interface ISaleService {
    List<Sale> listSales();
    Sale createSale(Sale sale);
    void updateSale(Sale sale);
    void deleteSale(Integer saleId);
    Sale findSaleById(Integer saleId);
}