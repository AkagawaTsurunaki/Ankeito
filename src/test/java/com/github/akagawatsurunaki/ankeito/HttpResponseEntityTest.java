package com.github.akagawatsurunaki.ankeito;

import com.github.akagawatsurunaki.ankeito.api.result.HttpResponseEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
public class HttpResponseEntityTest {

    @Test
    public void testEqualsAndHashCode() {
        String code = "200";
        String message = "test message";
        Object data = new Object();

        HttpResponseEntity entity1 = HttpResponseEntity.builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
        HttpResponseEntity entity2 = HttpResponseEntity.builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
        HttpResponseEntity entity3 = HttpResponseEntity.builder()
                .code("500")
                .message("another message")
                .data(new Object())
                .build();

        Assertions.assertEquals(entity1, entity2);
        Assertions.assertNotEquals(entity1, entity3);
        Assertions.assertEquals(entity1.hashCode(), entity2.hashCode());
        Assertions.assertNotEquals(entity1.hashCode(), entity3.hashCode());
    }

    @Test
    public void testToString() {
        String code = "200";
        String message = "test message";
        Object data = new Object();

        HttpResponseEntity entity = HttpResponseEntity.builder()
                .code(code)
                .message(message)
                .data(data)
                .build();

        String expectedString = "HttpResponseEntity(code=200, message=test message, data=" + data.toString() + ")";
        Assertions.assertEquals(expectedString, entity.toString());
    }
}
