package org.example.repository;

import org.example.model.CashReceipt;

public interface CashReceiptRepositoryInterface {
    void saveReceipt(CashReceipt receipt);

    CashReceipt findById(Long id) throws Exception;
}
