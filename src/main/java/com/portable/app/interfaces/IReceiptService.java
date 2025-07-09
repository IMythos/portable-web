package com.portable.app.interfaces;

import com.portable.app.entity.Receipt;
import java.util.List;

public interface IReceiptService {
    List<Receipt> listReceipts();
    Receipt createReceipt(Receipt receipt);
    void updateReceipt(Receipt receipt);
    void deleteReceipt(Integer receiptId);
    Receipt findReceiptById(Integer receiptId);
}