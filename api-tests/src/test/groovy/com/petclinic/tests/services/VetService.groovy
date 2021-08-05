package com.petclinic.tests.services

import com.petclinic.dto.Vet
import com.petclinic.mock.databuilders.Paths
import groovy.transform.CompileStatic
import io.restassured.response.Response

import static io.restassured.RestAssured.given
import static org.apache.http.HttpStatus.SC_CREATED
import static org.apache.http.HttpStatus.SC_OK

@CompileStatic
class VetService {

    static Response sentGetVetsRequest(String urlEnding = "") {
        given()
                .spec(RequestSpecs.basicSpec())
                .when()
                .get("${Paths.VETS}$urlEnding")
    }

    static List<Vet> getAllVets() {
        Arrays.asList(sentGetVetsRequest()
                .then()
                .statusCode(SC_OK)
                .extract().as(Vet[].class))
    }

    static Vet addVet(Vet requestBody) {
        given()
                .spec(RequestSpecs.basicSpec())
                .body(requestBody)
                .when()
                .post(Paths.VETS)
                .then()
                .statusCode(SC_CREATED)
                .extract().response().<Vet> as(Vet)
    }
}
