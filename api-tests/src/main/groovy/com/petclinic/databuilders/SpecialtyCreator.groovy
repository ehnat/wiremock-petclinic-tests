package com.petclinic.databuilders

import com.github.javafaker.Faker
import com.petclinic.dto.Specialty
import groovy.transform.CompileStatic

import static com.petclinic.databuilders.util.PropertiesValidator.validatePropertyNames

@CompileStatic
class SpecialtyCreator {

    private static final Faker FAKER = new Faker()

    private static final Map DEFAULT_SPECIALTY_PROPERTIES = [
            id  : 1,
            name: FAKER.name().name(),
    ]

    static Specialty sampleSpecialtyRequest(Map properties = [:]) {
        validatePropertyNames(DEFAULT_SPECIALTY_PROPERTIES, properties)

        properties = DEFAULT_SPECIALTY_PROPERTIES + properties

        return new Specialty(
                id: properties.id as Integer,
                name: properties.name as String
        )
    }
}
