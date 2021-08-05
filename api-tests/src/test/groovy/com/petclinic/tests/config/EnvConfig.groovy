package com.petclinic.tests.config

import org.aeonbits.owner.Config
import org.aeonbits.owner.Config.Key
import org.aeonbits.owner.Config.Sources

@Sources('classpath:env.properties')
interface EnvConfig extends Config {

    @Key('base-url')
    String baseUrl()
}
