package com.petclinic.dto

import groovy.transform.CompileStatic
import groovy.transform.builder.Builder

@Builder
@CompileStatic
class Specialty {

    int id
    String name
}
