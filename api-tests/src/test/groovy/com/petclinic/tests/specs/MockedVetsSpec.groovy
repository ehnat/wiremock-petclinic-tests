package com.petclinic.tests.specs

import com.petclinic.dto.Error
import com.petclinic.dto.Vet
import com.petclinic.mock.databuilders.VetStubsCreator
import com.petclinic.tests.services.VetService
import io.restassured.response.Response

import static com.petclinic.databuilders.VetCreator.sampleVetRequest
import static org.apache.http.HttpStatus.SC_NOT_FOUND

class MockedVetsSpec extends BaseSpec {

    def 'should return all vets'() {
        given: 'stub for get vets is created'
        VetStubsCreator.createGetVets(wireMockClient)

        when: 'request for getting all vets is sent'
        List<Vet> allVets = VetService.getAllVets()

        then: 'vets are returned'
        allVets.size() == 6
    }

    def 'should add vet with first name #vetFirstName'() {
        given: 'vet details'
        Map vetDetails = [
                id       : 1,
                firstName: vetFirstName,
                lastName : 'Bernard'
        ]
        Vet vetRequest = sampleVetRequest(vetDetails)

        and: 'stub for add vet is created'
        VetStubsCreator.createPostVet(wireMockClient, vetDetails)

        when: 'request for add vet is sent'
        Vet addedVet = VetService.addVet(vetRequest)

        then: 'added vet is returned'
        addedVet.firstName == vetFirstName

        where:
        vetFirstName << ['Lars', 'Inga', 'Ben']
    }

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
