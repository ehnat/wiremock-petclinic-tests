package com.petclinic.tests.specs.positive

import com.petclinic.dto.Vet
import com.petclinic.tests.services.VetService
import com.petclinic.tests.specs.BaseSpec

import static com.petclinic.databuilders.VetCreator.sampleVetRequest

class MockedVetsPositiveSpec extends BaseSpec {

    def 'should return all vets'() {
        given: 'stub for get vets is created'
        vetStubsCreator.createGetVets()

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
        vetStubsCreator.createPostVet(vetDetails)

        when: 'request for add vet is sent'
        Vet addedVet = VetService.addVet(vetRequest)

        then: 'added vet is returned'
        addedVet.firstName == vetFirstName

        where:
        vetFirstName << ['Lars', 'Inga', 'Ben']
    }
}
