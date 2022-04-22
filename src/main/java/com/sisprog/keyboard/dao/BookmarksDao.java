package com.sisprog.keyboard.dao;

import com.sisprog.keyboard.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarksDao extends JpaRepository<Bookmark, Long> {

    @Query(nativeQuery = true, value = "select * from bookmark where user_id = :userId order by created_date desc")
    List<Bookmark> getAllBookmarks(@Param("userId") Long userId);

    Optional<Bookmark> findBookmarkById(Long id);
}
