package com.petclinic.dto

import groovy.transform.CompileStatic
import groovy.transform.builder.Builder

@Builder
@CompileStatic
class Error {

    String timestamp
    int status
    String error
    String message
    String path
}
