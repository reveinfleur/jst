package com.io.jst.repository;

import com.io.jst.model.entity.Shop;
import com.io.jst.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {



}
