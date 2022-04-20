package com.sisprog.keyboard.dto;

import com.sisprog.keyboard.domain.Bookmark;
import lombok.Data;

@Data
public class BookmarkDto {
    private Long id;
    private String url;
    private String title;
    private String description;
    private String img;
    private String source;
    private Long sourceDate;
    private Long createdDate;

    public static Bookmark of(BookmarkDto dto) {
        Bookmark entity = new Bookmark();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setImg(dto.getImg());
        entity.setSource(dto.getSource());
        entity.setSourceDate(dto.getSourceDate());
        entity.setCreatedDate(dto.getCreatedDate());
        return entity;
    }

    public static BookmarkDto of(Bookmark entity) {
        BookmarkDto dto = new BookmarkDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setImg(entity.getImg());
        dto.setSource(entity.getSource());
        dto.setSourceDate(entity.getSourceDate());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

}
