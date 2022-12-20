package org.example.repository;

import org.example.model.CashReceipt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CashReceiptRepository implements CashReceiptRepositoryInterface {

    private final List<CashReceipt> receipts = new ArrayList<>();

    @Override
    public void saveReceipt(CashReceipt receipt){
        receipts.add(receipt);
    }

    @Override
    public CashReceipt findById(Long id) throws Exception {
        Optional<CashReceipt> first = receipts.stream()
                .filter(receipt -> Objects.equals(receipt.getId(), id))
                .findFirst();
        return first.orElseThrow(Exception::new);
    }
}
