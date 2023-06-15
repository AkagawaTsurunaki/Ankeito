package com.github.akagawatsurunaki.ankeito;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnkeitoApplicationTest {
    @Test
    public void testMain() {
        String[] args = {""};
        AnkeitoApplication.main(args);
    }
}
