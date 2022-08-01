package com.io.jst.repository;


import com.io.jst.model.entity.UserCreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<UserCreditCard, Long> {
    Optional<UserCreditCard> findByCardName(String cardName);
}
