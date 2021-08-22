package com.petclinic.mock.databuilders

import org.apache.commons.io.IOUtils
import java.nio.charset.StandardCharsets

class MockResponse {

    static String read(String fileName) {
        InputStream stream = MockResponse.class.classLoader.getResourceAsStream(fileName)
        asString(stream)
    }

    private static String asString(InputStream stream) {
        try (InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            IOUtils.toString(streamReader)
        } catch (IOException e) {
            e.printStackTrace()
        }
    }
}
