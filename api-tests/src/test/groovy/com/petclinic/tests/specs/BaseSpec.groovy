package com.petclinic.tests.specs

import com.github.tomakehurst.wiremock.client.WireMock
import com.petclinic.mock.databuilders.VetStubsCreator
import spock.lang.Shared
import spock.lang.Specification

import static com.petclinic.tests.mock.Wiremock.resetStubs

class BaseSpec extends Specification {

    @Shared
    WireMock wireMockClient

    @Shared
    VetStubsCreator vetStubsCreator

    def setupSpec() {
        wireMockClient = new WireMock('https', 'localhost', 443)
        vetStubsCreator = new VetStubsCreator(wireMockClient)
    }

    def setup() {
        resetStubs(wireMockClient)
    }

    def cleanup() {
        resetStubs(wireMockClient)
    }
}
