package com.sisprog.keyboard.service;

import com.sisprog.keyboard.dao.BookmarksDao;
import com.sisprog.keyboard.domain.Bookmark;
import com.sisprog.keyboard.dto.BookmarkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookmarksServiceImpl implements BookmarksService {

    private final BookmarksDao bookmarksDao;

    @Override
    public List<BookmarkDto> getAll(Long userId) {
        return bookmarksDao.getAllBookmarks(userId).stream().map(BookmarkDto::of).collect(Collectors.toList());
    }

    @Override
    public BookmarkDto getById(Long id) {
        return BookmarkDto.of(bookmarksDao.findBookmarkById(id).orElse(new Bookmark()));
    }

    @Override
    public BookmarkDto save(BookmarkDto bookmarkDto) {
        return BookmarkDto.of(bookmarksDao.save(BookmarkDto.of(bookmarkDto)));
    }

    @Override
    public List<BookmarkDto> saveAll(List<BookmarkDto> bookmarks) {
        return bookmarksDao
                .saveAll(
                        bookmarks.stream().map(BookmarkDto::of).collect(Collectors.toList())
                )
                .stream()
                .map(BookmarkDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        bookmarksDao.deleteById(id);
    }
}
