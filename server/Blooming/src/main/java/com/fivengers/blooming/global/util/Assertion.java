package com.fivengers.blooming.global.util;

import com.fivengers.blooming.global.exception.global.InvalidMethodUsecaseException;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Assertion<T> {
    private T param;
    private Predicate<T> predicate;

    private Assertion(T param) {
        this.param = param;
    }

    public static <T> Assertion<T> with(T param) {
        return new Assertion<>(param);
    }

    public Assertion<T> setValidation(Predicate<T> predicate) {
        this.predicate = predicate;
        return this;
    }

    public <X extends Throwable> void validateOrThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (predicate == null) {
            throw new InvalidMethodUsecaseException();
        }
        if (!predicate.test(param)) {
            throw exceptionSupplier.get();
        }
    }
}
