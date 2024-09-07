package com.seohee.fcfsordermsa.domain.wishlist.repository;

import com.seohee.fcfsordermsa.domain.user.entity.User;
import com.seohee.fcfsordermsa.domain.wishlist.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

    List<WishList> findByUser(User user);
}
