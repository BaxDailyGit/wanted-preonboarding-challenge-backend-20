package com.example.WantedMarket.repository;
import com.example.WantedMarket.domain.entity.Product;
import com.example.WantedMarket.domain.entity.Transaction;
import com.example.WantedMarket.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByBuyer(User buyer);
    List<Transaction> findBySeller(User seller);
    List<Transaction> findByProduct(Product product);
}