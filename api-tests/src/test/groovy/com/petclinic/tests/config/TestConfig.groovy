package com.petclinic.tests.config

import groovy.transform.CompileStatic
import org.aeonbits.owner.ConfigFactory

@CompileStatic
class TestConfig {

    private final EnvConfig envConfigVar = ConfigFactory.create(EnvConfig, [env: 'env'])

    static EnvConfig getEnvConfig() {
        LazyHolder.INSTANCE.envConfigVar
    }

    private static class LazyHolder {

        private static final TestConfig INSTANCE = new TestConfig()
    }
}
