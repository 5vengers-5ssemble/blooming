package com.fivengers.blooming.global.util;

import com.fivengers.blooming.global.exception.global.InvalidMethodUsecaseException;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BiAssertion<T, U> {

    private T param1;
    private U param2;
    private BiPredicate<T, U> biPredicate;

    private BiAssertion(T param1, U param2) {
        this.param1 = param1;
        this.param2 = param2;
    }

    public static <T, U> BiAssertion<T, U> with(T param1, U param2) {
        return new BiAssertion<>(param1, param2);
    }

    public BiAssertion<T, U> setValidation(BiPredicate<T, U> biPredicate) {
        this.biPredicate = biPredicate;
        return this;
    }

    public <X extends Throwable> void validateOrThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (biPredicate == null) {
            throw new InvalidMethodUsecaseException();
        }
        if (!biPredicate.test(param1, param2)) {
            throw exceptionSupplier.get();
        }
    }

}
