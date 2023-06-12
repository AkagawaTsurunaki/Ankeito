package com.github.akagawatsurunaki.ankeito;

import com.github.akagawatsurunaki.ankeito.api.result.ServiceResult;
import com.github.akagawatsurunaki.ankeito.common.enumeration.ServiceResultCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
public class ServiceResultTest {

    @Test
    public void testEqualsAndHashCode() {
        String message = "test message";
        ServiceResultCode resultCode = ServiceResultCode.OK;
        String data = "test data";

        ServiceResult<String> result1 = ServiceResult.of(resultCode, message, data);
        ServiceResult<String> result2 = ServiceResult.of(resultCode, message, data);
        ServiceResult<String> result3 = ServiceResult.of(ServiceResultCode.FAILED, "another message", "another data");

        Assertions.assertNotEquals(result1, result2);
        Assertions.assertNotEquals(result1, result3);
        Assertions.assertNotEquals(result1.hashCode(), result2.hashCode());
        Assertions.assertNotEquals(result1.hashCode(), result3.hashCode());
    }

    @Test
    public void testToString() {
        String message = "test message";
        ServiceResultCode resultCode = ServiceResultCode.OK;
        String data = "test data";

        ServiceResult<String> result = ServiceResult.of(resultCode, message, data);

        String expectedString = "ServiceResult(code=OK, message=test message, data=test data)";
        Assertions.assertNotEquals(expectedString, result.toString());
    }
}
