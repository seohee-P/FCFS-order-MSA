package com.seohee.fcfsordermsa.domain.wishlist.repository;

import com.seohee.fcfsordermsa.domain.wishlist.entity.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListItemRepository extends JpaRepository<WishListItem, Long> {
}
