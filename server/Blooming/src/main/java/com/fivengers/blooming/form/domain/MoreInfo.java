package com.fivengers.blooming.form.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MoreInfo {

    private String description;
    private String albumImg;
    private String tracklistImg;

    public MoreInfo(String description, String albumImg, String tracklistImg) {
        this.description = description;
        this.albumImg = albumImg;
        this.tracklistImg = tracklistImg;
    }
}
