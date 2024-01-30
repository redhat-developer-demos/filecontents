package com.redhat.appdev.filecontents;

// camel-k: language=java

import org.apache.camel.builder.RouteBuilder;

public class filecontents extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        rest("/api").get("/filecontents").to("direct:filecontents");

        // Write your routes here, for example:
        from("direct:filecontents").routeId("java")
                .pollEnrich().simple("file:{{file.path}}?fileName={{file.name}}&noop=true&idempotent=false")
                .convertBodyTo(String.class)
                .log("${body}");
    }
}
