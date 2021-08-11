package com.collections.comics.models.requests;

import org.springframework.core.SpringVersion;

public class AddComicRequest {

    private Long userId;
    private Long comicId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getComicId() {
        return comicId;
    }

    public void setComicId(Long comicId) {
        this.comicId = comicId;
    }
}
