package com.ricoh.test.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class NobelApiMock extends WireMockServer {

    public NobelApiMock(int port) {
        super(port);
    }


    public void startMockServer() {
        start();
        stubFor(
                WireMock.get(WireMock.urlPathMatching("/nobelPrize/category/2022"))
                        .willReturn(
                                WireMock.aResponse()
                                        .withStatus(HttpStatus.OK.value())
                                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                        .withBodyFile("mock/nobel.json"))
        );

    }
}
