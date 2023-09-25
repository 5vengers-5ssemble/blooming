package com.fivengers.blooming.form.adapter.out.persistence.mapper;

import com.fivengers.blooming.form.adapter.out.persistence.entity.MoreInfoInForm;
import com.fivengers.blooming.form.domain.MoreInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MoreInfoMapper {

    public MoreInfo toDomain(MoreInfoInForm moreInfo) {

        return new MoreInfo(moreInfo.getDescription(),
                moreInfo.getAlbumImg(),
                moreInfo.getTracklistImg());
    }

    public MoreInfoInForm toInForm(MoreInfo moreInfo) {

        return new MoreInfoInForm(moreInfo.getDescription(),
                moreInfo.getAlbumImg(),
                moreInfo.getTracklistImg());
    }
}
