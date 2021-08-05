package com.petclinic.dto

import groovy.transform.CompileStatic
import groovy.transform.builder.Builder

@Builder
@CompileStatic
class Vet {

    int id
    String firstName
    String lastName
    List<Specialty> specialties
}
