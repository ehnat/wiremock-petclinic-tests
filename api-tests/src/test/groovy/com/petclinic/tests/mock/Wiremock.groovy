package com.petclinic.tests.mock

import com.github.tomakehurst.wiremock.client.WireMock
import groovy.transform.CompileStatic

@CompileStatic
class Wiremock {

    def static resetStubs(WireMock wireMockClient) {
        wireMockClient.resetMappings()
    }
}
