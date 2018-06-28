package com.mstzn.testpractice.testdouble;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.verify;
/*
     verify that the add method is called and also assert that the item was added to the list.
 */

@RunWith(MockitoJUnitRunner.class)
public class SpyTestDouble {

    @Spy
    private List<String> listSpy = new ArrayList<>();

    @Test
    public void it_should_spy_return_real_values() {
        //given
        String val = "test";

        //when
        listSpy.add(val);

        //then
        verify(listSpy).add("test");
        assertThat(listSpy).containsOnly("test");
    }
}
