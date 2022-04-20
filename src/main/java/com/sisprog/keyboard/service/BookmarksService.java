package com.sisprog.keyboard.service;

import com.sisprog.keyboard.dto.BookmarkDto;

import java.util.List;

public interface BookmarksService {

    List<BookmarkDto> getAll();

    BookmarkDto getByUrl(String url);

    BookmarkDto save(BookmarkDto bookmarkDto);

    List<BookmarkDto> saveAll(List<BookmarkDto> bookmarks);

    void deleteById(Long id);
}
