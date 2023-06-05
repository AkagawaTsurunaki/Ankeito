package com.github.akagawatsurunaki.ankeito;

import com.github.akagawatsurunaki.ankeito.api.param.AddUserParam;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    public void testToString() {
        val param1 = new AddUserParam();
        val param2 = new AddUserParam();
        System.out.println("param1.toString() = " + param1);
        System.out.println("param2.toString() = " + param2);
    }
}
