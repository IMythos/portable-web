package com.portable.app.service;

import com.portable.app.entity.Receipt;
import com.portable.app.interfaces.IReceiptService;
import com.portable.app.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ReceiptServiceImpl implements IReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Receipt> listReceipts() {
        return receiptRepository.listReceipts();
    }

    @Override
    @Transactional
    public Receipt createReceipt(Receipt receipt) {
        Integer newId = receiptRepository.insertReceipt(
            receipt.getDocument(),
            receipt.getSeries(),
            receipt.getNumber(),
            receipt.getIssueDate(),
            receipt.getDueDate(),
            receipt.getCurrency()
        );
        receipt.setReceiptId(newId);
        return receipt;
    }

    @Override
    @Transactional
    public void updateReceipt(Receipt receipt) {
        receiptRepository.updateReceipt(
            receipt.getReceiptId(),
            receipt.getDocument(),
            receipt.getSeries(),
            receipt.getNumber(),
            receipt.getIssueDate(),
            receipt.getDueDate(),
            receipt.getCurrency()
        );
    }

    @Override
    @Transactional
    public void deleteReceipt(Integer receiptId) {
        receiptRepository.deleteReceipt(receiptId);
    }

    @Override
    @Transactional(readOnly = true)
    public Receipt findReceiptById(Integer receiptId) {
        return receiptRepository.findById(receiptId).orElse(null);
    }
}