package com.fivengers.blooming.artistscrap.application;

import com.fivengers.blooming.artistscrap.domain.ArtistScrapRecord;
import java.util.function.Consumer;

@FunctionalInterface
public interface ChangeCountConsumer <T> {
    void changeCount(T t);
}
