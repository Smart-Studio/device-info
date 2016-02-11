package com.smarstudio.deviceinfo;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FirstTest {

    @Test
    public void testSum() throws Exception {
        int a = 3 + 1;
        assertThat(a, is(4));

    }
}
