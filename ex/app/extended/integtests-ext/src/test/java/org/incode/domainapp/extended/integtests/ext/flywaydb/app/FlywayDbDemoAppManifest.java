package org.incode.domainapp.extended.integtests.ext.flywaydb.app;

import org.apache.isis.applib.AppManifestAbstract;

import org.incode.domainapp.extended.module.fixtures.shared.ExampleDomDemoDomSubmodule;

/**
 * Bootstrap the application.
 */
public class FlywayDbDemoAppManifest extends AppManifestAbstract {

    public static final Builder BUILDER = Builder.forModules(
            ExampleDomDemoDomSubmodule.class
    );

    public FlywayDbDemoAppManifest() {
        super(BUILDER);
    }

}