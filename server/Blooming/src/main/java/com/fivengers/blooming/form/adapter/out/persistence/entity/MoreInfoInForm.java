package com.fivengers.blooming.form.adapter.out.persistence.entity;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MoreInfoInForm {

    private String description;
    private String albumImg;
    private String tracklistImg;

    public MoreInfoInForm(String description, String albumImg, String tracklistImg) {
        this.description = description;
        this.albumImg = albumImg;
        this.tracklistImg = tracklistImg;
    }
}
