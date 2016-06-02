package com.smartstudio.deviceinfo.utils;

import com.smartstudio.deviceinfo.ui.PropertyLayout;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.Field;

import static org.powermock.api.mockito.PowerMockito.mock;

public final class TestUtils {

    public static void mockStaticField(Class clazz, String field, Object value) throws IllegalAccessException {
        Field f = PowerMockito.field(clazz, field);
        f.set(clazz, value);
    }

    public static <T> T getPrivateStaticField(Class clazz, String fieldName) {
        return Whitebox.getInternalState(clazz, fieldName);
    }

    public static PropertyLayout mockPropertyLayout(){
        return mock(PropertyLayout.class);
    }

    private TestUtils() {
    }
}
