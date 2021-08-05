package com.petclinic.tests.specs

import com.github.tomakehurst.wiremock.client.WireMock
import spock.lang.Shared
import spock.lang.Specification

import static com.petclinic.tests.mock.Wiremock.resetStubs

class BaseSpec extends Specification {

    @Shared
    WireMock wireMockClient

    def setupSpec() {
        wireMockClient = new WireMock('https', 'localhost', 443)
    }

    def setup() {
        resetStubs(wireMockClient)
    }

//    def cleanup(){
//        resetStubs(wireMockClient)
//    }
}
