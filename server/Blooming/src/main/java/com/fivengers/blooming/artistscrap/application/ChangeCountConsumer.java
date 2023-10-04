package com.fivengers.blooming.artistscrap.application;

import com.fivengers.blooming.artistscrap.domain.ArtistScrapRecord;
import java.util.function.Consumer;

@FunctionalInterface
public interface ChangeCountConsumer <T extends ArtistScrapRecord> {
    void changeCount(T t);
}
