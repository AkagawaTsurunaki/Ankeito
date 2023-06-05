package com.github.akagawatsurunaki.ankeito;

import com.github.akagawatsurunaki.ankeito.api.param.AddUserParam;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class AddUserParamTest {
    @Test
    public void testHashCode() {
        AddUserParam param1 = new AddUserParam();
        AddUserParam param2 = new AddUserParam();

        if (param1.hashCode() == param2.hashCode()) {
            System.out.println("equal");
        }

        param1.setUsername("test");
        if (param1.hashCode() == param2.hashCode()) {
            System.out.println("equal");
        }

        param2.setUsername("test");
        if (param1.hashCode() == param2.hashCode()) {
            System.out.println("equal");
        }
    }

    @Test
    public void testEquals() {
        AddUserParam param1 = new AddUserParam();
        AddUserParam param2 = new AddUserParam();

        if (param1.equals(param2)) {
            System.out.println("equal");
        }

        param1.setUsername("test");
        if (param1.equals(param2)) {
            System.out.println("equal");
        }

        param2.setUsername("test");
        if (param1.equals(param2)) {
            System.out.println("equal");
        }
    }
}
