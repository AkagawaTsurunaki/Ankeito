package com.github.akagawatsurunaki.ankeito;

import com.github.akagawatsurunaki.ankeito.api.param.AddUserParam;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AddUserParamTest {
    @Test
    public void testHashCode() {
        AddUserParam param1 = new AddUserParam();
        AddUserParam param2 = new AddUserParam();

        assertEquals(param1.hashCode(), param2.hashCode());

        param1.setUsername("test");
        assertNotEquals(param1.hashCode(), param2.hashCode());

        param2.setUsername("test");
        assertEquals(param1.hashCode(), param2.hashCode());
    }

    @Test
    public void testEquals() {
        AddUserParam param1 = new AddUserParam();
        AddUserParam param2 = new AddUserParam();

        assertEquals(param1, param2);
        assertEquals(param2, param1);

        param1.setUsername("test");
        assertNotEquals(param1, param2);
        assertNotEquals(param2, param1);

        param2.setUsername("test");
        assertEquals(param1, param2);
        assertEquals(param2, param1);

        LocalDate today = LocalDate.now();
        Date startTime1 = java.sql.Timestamp.valueOf(today.atStartOfDay());
        Date stopTime1 = java.sql.Timestamp.valueOf(today.plusDays(1).atStartOfDay());
        Date startTime2 = java.sql.Timestamp.valueOf(today.atStartOfDay());
        Date stopTime2 = java.sql.Timestamp.valueOf(today.plusDays(1).atStartOfDay());

        param1.setStartTime(startTime1);
        param2.setStartTime(startTime2);
        param1.setStopTime(stopTime1);
        param2.setStopTime(stopTime2);

        assertEquals(param1, param2);
        assertEquals(param2, param1);

        param1.setStopTime(stopTime2);
        assertNotEquals(param1, param2);
        assertNotEquals(param2, param1);
    }

    @Test
    public void testToString() {
        AddUserParam param = new AddUserParam();
        String expected = "AddUserParam(username=null, password=null, startTime=null, stopTime=null)";

        assertEquals(expected, param.toString());

        LocalDate today = LocalDate.now();
        Date startTime = java.sql.Timestamp.valueOf(today.atStartOfDay());
        Date stopTime = java.sql.Timestamp.valueOf(today.plusDays(1).atStartOfDay());

        param.setUsername("test");
        param.setPassword("123456");
        param.setStartTime(startTime);
        param.setStopTime(stopTime);

        expected = "AddUserParam(username=test, password=123456, startTime=" + startTime + ", stopTime=" + stopTime + ")";
        assertEquals(expected, param.toString());
    }
}
