package com.petclinic.databuilders

import com.github.javafaker.Faker
import com.petclinic.dto.Specialty
import com.petclinic.dto.Vet
import groovy.transform.CompileStatic

import static com.petclinic.databuilders.SpecialtyCreator.sampleSpecialtyRequest
import static com.petclinic.databuilders.util.PropertiesValidator.validatePropertyNames

@CompileStatic
class VetCreator {

    private static final Faker FAKER = new Faker()

    private static final Map DEFAULT_VET_PROPERTIES = [
            id         : 1,
            firstName  : FAKER.name().firstName(),
            lastName   : FAKER.name().lastName(),
            specialties: [sampleSpecialtyRequest([
                    id  : 2,
                    name: 'surgeon',
            ])]
    ]

    static Vet sampleVetRequest(Map properties = [:]) {
        validatePropertyNames(DEFAULT_VET_PROPERTIES, properties)

        properties = DEFAULT_VET_PROPERTIES + properties

        return new Vet(
                id: properties.id as Integer,
                firstName: properties.firstName as String,
                lastName: properties.lastName as String,
                specialties: properties.specialties as List<Specialty>
        )
    }
}
