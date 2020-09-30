package com.struggzard.samples.rxjava;

import io.reactivex.Observable;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ZipWithOperatorTest {

    /**
     * Two observable sources (iterable at this case)
     *
     * expected result: "A", "B", "C" because shorter source `List.of("1", "2", "3")` has only 3 items
     */
    @Test
    public void test1() {
        Observable<String> result = Observable.fromIterable(List.of("A", "B", "C", "D"))
                .zipWith(Observable.fromIterable(List.of("1", "2", "3")), (a, b) -> a);

        result.test()
                .assertComplete()
                .assertValueCount(3)
                .assertValues("A", "B", "C");
    }

    /**
     * Two observable sources (iterable at this case)
     *
     * expected result: "A", "B", "C", "D", because shorter source `List.of("A", "B", "C", "D")` has only 4 items
     */
    @Test
    public void test2() {
        Observable<String> result = Observable.fromIterable(List.of("A", "B", "C", "D"))
                .zipWith(Observable.fromIterable(List.of("1", "2", "3", "4", "5")), (a, b) -> a);

        result.test()
                .assertComplete()
                .assertValueCount(4)
                .assertValues("A", "B", "C", "D");
    }

}
