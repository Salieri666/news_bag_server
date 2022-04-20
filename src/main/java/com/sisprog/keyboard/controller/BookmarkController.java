package com.sisprog.keyboard.controller;

import com.sisprog.keyboard.dto.BookmarkDto;
import com.sisprog.keyboard.service.BookmarksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarksService bookmarksService;

    @GetMapping(value = "/getAll", produces = APPLICATION_JSON_VALUE)
    public List<BookmarkDto> getAll() {
        return bookmarksService.getAll();
    }

    @GetMapping(value = "/getByUrl/{url}", produces = APPLICATION_JSON_VALUE)
    public BookmarkDto getByUrl(@PathVariable("url") String url) {
        return bookmarksService.getByUrl(url);
    }

    @PutMapping(value = "/save", produces = APPLICATION_JSON_VALUE)
    public BookmarkDto save(@RequestBody BookmarkDto dto) {
        return bookmarksService.save(dto);
    }

    @PutMapping(value = "/saveAll", produces = APPLICATION_JSON_VALUE)
    public List<BookmarkDto> saveAll(@RequestBody List<BookmarkDto> bookmarks) {
        return bookmarksService.saveAll(bookmarks);
    }

    @DeleteMapping(value = "/delete/{id}", produces = APPLICATION_JSON_VALUE)
    public void deleteById(@PathVariable("id") Long id) {
        bookmarksService.deleteById(id);
    }
}
