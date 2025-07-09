package com.portable.app.service;

import com.portable.app.entity.Sale;
import com.portable.app.interfaces.ISaleService;
import com.portable.app.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SaleServiceImpl implements ISaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Sale> listSales() {
        return saleRepository.listSales();
    }

    @Override
    @Transactional
    public Sale createSale(Sale sale) {
        Integer newId = saleRepository.insertSale(
            sale.getClient().getClientId(),
            sale.getUser().getUserId(),
            sale.getReceipt().getReceiptId(),
            sale.getPaymentType(),
            sale.getSaleType(),
            sale.getTotal()
        );
        sale.setSaleId(newId);
        return sale;
    }

    @Override
    @Transactional
    public void updateSale(Sale sale) {
        saleRepository.updateSale(
            sale.getSaleId(),
            sale.getClient().getClientId(),
            sale.getUser().getUserId(),
            sale.getReceipt().getReceiptId(),
            sale.getPaymentType(),
            sale.getSaleType(),
            sale.getTotal()
        );
    }

    @Override
    @Transactional
    public void deleteSale(Integer saleId) {
        saleRepository.deleteSale(saleId);
    }

    @Override
    @Transactional(readOnly = true)
    public Sale findSaleById(Integer saleId) {
        return saleRepository.findById(saleId).orElse(null);
    }
}