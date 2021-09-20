package com.petclinic.mock.databuilders

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.stubbing.StubMapping

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
import static com.github.tomakehurst.wiremock.client.WireMock.containing
import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.matchingJsonPath
import static com.github.tomakehurst.wiremock.client.WireMock.notFound
import static com.github.tomakehurst.wiremock.client.WireMock.post
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import static org.apache.http.HttpHeaders.CONTENT_TYPE
import static org.apache.http.HttpStatus.SC_CREATED
import static org.apache.http.HttpStatus.SC_OK

class VetStubsCreator {

    private WireMock wireMockClient

    VetStubsCreator(WireMock wireMockClient) {
        this.wireMockClient = wireMockClient
    }

    StubMapping createGetVets() {
        wireMockClient.register(
                get(urlEqualTo(Paths.VETS))
                        .atPriority(1)
                        .willReturn(responseAsJsonFile('__files/responseGetVets.json', SC_OK))
        )
    }

    StubMapping createError404(String url) {
        wireMockClient.register(
                get(urlPathEqualTo("${Paths.VETS}$url"))
                        .willReturn(notFound()
                                .withHeader(CONTENT_TYPE, 'application/json')
                                .withBody(MockResponse.read('__files/responseError404.json'))
                        )
        )
    }

    StubMapping createPostVet(Map vetDetails) {
        wireMockClient.register(
                post(urlEqualTo(Paths.VETS))
                        .atPriority(1)
                        .withHeader(CONTENT_TYPE, containing('application/json'))
                        .withRequestBody(matchingJsonPath("\$[?(@.id == ${vetDetails.get('id')})]"))
                        .withRequestBody(matchingJsonPath("\$[?(@.firstName == \'${vetDetails.get('firstName')}\')]"))
                        .withRequestBody(matchingJsonPath("\$[?(@.lastName == \'${vetDetails.get('lastName')}\')]"))
                        .withRequestBody(matchingJsonPath('$[?(@.specialties[0].id == 2)]'))
                        .withRequestBody(matchingJsonPath('$[?(@.specialties[0].name == \'surgeon\')]'))
                        .willReturn(responseAsJsonFile('__files/responsePostVet.json', SC_CREATED))
        )
    }

    private static ResponseDefinitionBuilder responseAsJsonFile(String jsonFile, int status) {
        aResponse()
                .withStatus(status)
                .withHeader(CONTENT_TYPE, 'application/json')
                .withBody(MockResponse.read(jsonFile))
    }
}
