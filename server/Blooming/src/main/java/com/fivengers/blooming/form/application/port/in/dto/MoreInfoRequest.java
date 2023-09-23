package com.fivengers.blooming.form.application.port.in.dto;

import com.fivengers.blooming.form.domain.MoreInfo;

public record MoreInfoRequest(String album_desc,
                              String track_list,
                              String album_img) {

    public MoreInfo toDomain() {
        return new MoreInfo(album_desc, track_list, album_img);
    }
}
