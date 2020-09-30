package com.struggzard.samples.rxjava;

import io.reactivex.Observable;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MapUsedForFilterTest {

    @Test
    public void test1() {
        Observable<String> result = Observable.fromIterable(List.of("A1", "B2", "C1", "D2"))
                .flatMap(s -> s.contains("1") ? Observable.just(s) : Observable.empty());

        result.test()
                .assertComplete()
                .assertValueCount(2)
                .assertValues("A1", "C1");
    }

    private Observable<Boolean> verify(String value) {
        return Observable.just(value.contains("1"));
    }

    @Test
    public void test2() {
        Observable<String> result = Observable.fromIterable(List.of("A1", "B2", "C1", "D2"))
                .flatMap(s -> Observable.just(s.contains("1"))
                        .flatMap(r -> r ? Observable.just(s) : Observable.empty()));

        result.test()
                .assertComplete()
                .assertValueCount(2)
                .assertValues("A1", "C1");
    }
}
