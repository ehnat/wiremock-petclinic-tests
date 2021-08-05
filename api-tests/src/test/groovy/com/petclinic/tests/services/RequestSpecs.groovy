package com.petclinic.tests.services

import groovy.transform.CompileStatic
import io.restassured.builder.RequestSpecBuilder
import io.restassured.config.HttpClientConfig
import io.restassured.filter.Filter
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification

import static com.petclinic.tests.config.TestConfig.envConfig
import static io.restassured.config.HttpClientConfig.httpClientConfig
import static io.restassured.config.RestAssuredConfig.newConfig
import static org.apache.http.params.CoreConnectionPNames.CONNECTION_TIMEOUT
import static org.apache.http.params.CoreConnectionPNames.SO_TIMEOUT

@CompileStatic
class RequestSpecs {

    private static final String APPLICATION_JSON = ContentType.JSON
    private static final int DEFAULT_CONNECTION_TIMEOUT_IN_MILLIS = 10_000
    private static final int DEFAULT_SOCKET_TIMEOUT_IN_MILLIS = 10_000

    private static final List<Filter> LOGGING_FILTERS = [new RequestLoggingFilter(), new ResponseLoggingFilter()]
    private static final HttpClientConfig HTTP_CLIENT_CONFIG = httpClientConfig().setParam(CONNECTION_TIMEOUT, DEFAULT_CONNECTION_TIMEOUT_IN_MILLIS)
            .setParam(SO_TIMEOUT, DEFAULT_SOCKET_TIMEOUT_IN_MILLIS)

    private RequestSpecs() {
    }

    static RequestSpecification basicSpec(String accept = APPLICATION_JSON) {
        new RequestSpecBuilder()
                .setBaseUri(getEnvConfig().baseUrl())
                .setConfig(newConfig().httpClient(HTTP_CLIENT_CONFIG))
                .addFilters(LOGGING_FILTERS)
                .setAccept(accept)
                .setContentType(ContentType.JSON)
                .build()
    }
}
