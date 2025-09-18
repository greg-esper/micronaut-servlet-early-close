package com.example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.exceptions.ResponseClosedException;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class ResponseSizeTest {

    @Inject
    EmbeddedServer server;

    @Test
    void serverDoesNotCloseResponseEarly() {
        try {
            final int size = 1000000; // 1MB response
            try (HttpClient client = HttpClient.create(server.getURL())) {
                for (int i = 0; i < 100; i++) {
                    int responseLength = client.toBlocking().retrieve(
                        HttpRequest.GET("/?size=" + size)
                    ).length();
                    assertEquals(size, responseLength);
                }
            }
        } catch (ResponseClosedException e) {
            fail("Connection closed early", e);
        }
    }
}