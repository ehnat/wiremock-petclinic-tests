package com.petclinic.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class WiremockRunner {

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);

        WireMockServer wireMockServer = new WireMockServer(
                options()
                        .port(port)
                        .httpsPort(443)
                        .notifier(new ConsoleNotifier(true))
                        .extensions(new ResponseTemplateTransformer(true))
        );

        wireMockServer.start();


    }
}
