package com.sisprog.keyboard.dao;

import com.sisprog.keyboard.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarksDao extends JpaRepository<Bookmark, Long> {

    @Query(nativeQuery = true, value = "select * from bookmark order by created_date desc")
    List<Bookmark> getAllBookmarks();

    Optional<Bookmark> findBookmarkByUrl(String url);
}
