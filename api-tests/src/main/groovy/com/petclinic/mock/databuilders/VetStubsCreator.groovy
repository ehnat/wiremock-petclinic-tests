package com.petclinic.mock.databuilders

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.stubbing.StubMapping

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
import static com.github.tomakehurst.wiremock.client.WireMock.containing
import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.matchingJsonPath
import static com.github.tomakehurst.wiremock.client.WireMock.post
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import static org.apache.http.HttpHeaders.CONTENT_TYPE
import static org.apache.http.HttpStatus.SC_CREATED
import static org.apache.http.HttpStatus.SC_OK

class VetStubsCreator {

    static StubMapping createGetVets(WireMock wireMockClient) {
        wireMockClient.register(
                get(urlEqualTo(Paths.VETS))
                        .atPriority(1)
                        .willReturn(responseAsJsonFile('responseGetVets.json'))
        )
    }

    static StubMapping createError404(WireMock wireMockClient, String url) {
        wireMockClient.register(
                get(urlPathEqualTo("${Paths.VETS}$url"))
                        .willReturn(WireMock.notFound()
                                .withHeader(CONTENT_TYPE, 'application/json')
                                .withBodyFile('responseError404.json')
                        )
        )
    }

    static StubMapping createPostVet(WireMock wireMockClient, Map vetDetails) {
        wireMockClient.register(
                post(urlEqualTo(Paths.VETS))
                        .atPriority(1)
                        .withHeader(CONTENT_TYPE, containing('application/json'))
                        .withRequestBody(matchingJsonPath("\$[?(@.id == ${vetDetails.get('id')})]"))
                        .withRequestBody(matchingJsonPath("\$[?(@.firstName == \'${vetDetails.get('firstName')}\')]"))
                        .withRequestBody(matchingJsonPath("\$[?(@.lastName == \'${vetDetails.get('lastName')}\')]"))
                        .withRequestBody(matchingJsonPath('$[?(@.specialties[0].id == 2)]'))
                        .withRequestBody(matchingJsonPath('$[?(@.specialties[0].name == \'surgeon\')]'))
                        .willReturn(aResponse()
                                .withStatus(SC_CREATED)
                                .withHeader(CONTENT_TYPE, 'application/json')
                                .withBodyFile('responsePostVet.json')
                        )
        )
    }

    private static ResponseDefinitionBuilder responseAsJsonFile(String jsonFile) {
        aResponse()
                .withStatus(SC_OK)
                .withHeader(CONTENT_TYPE, 'application/json')
                .withBodyFile(jsonFile)
    }
}
