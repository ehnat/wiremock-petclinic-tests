package com.petclinic.tests.specs.negative

import com.petclinic.dto.Error
import com.petclinic.mock.databuilders.VetStubsCreator
import com.petclinic.tests.services.VetService
import com.petclinic.tests.specs.BaseSpec
import io.restassured.response.Response

import static org.apache.http.HttpStatus.SC_NOT_FOUND

class MockedVetsNegativeSpec extends BaseSpec {

    def 'should return error 404 when get vets request is called with url ending = #incorrectUrlEnding'() {
        given: 'stub for error 404 is created'
        VetStubsCreator.createError404(wireMockClient, incorrectUrlEnding)

        when: 'request for getting all vets is sent (with url ending = #incorrectUrlEnding)'
        Response response = VetService.sentGetVetsRequest(incorrectUrlEnding)

        then: 'error 404 is returned'
        response.statusCode == SC_NOT_FOUND

        with(response.as(Error)) {
            timestamp
            status == SC_NOT_FOUND
            error == 'Not Found'
            message.isBlank()
            path == "/petclinic/api/vets$incorrectUrlEnding"
        }

        where:
        incorrectUrlEnding << ['NO', 'test', 'NOT_EXISTING']
    }
}
