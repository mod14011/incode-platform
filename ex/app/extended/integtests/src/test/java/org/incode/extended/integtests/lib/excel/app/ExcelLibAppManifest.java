package org.incode.extended.integtests.lib.excel.app;

import org.apache.isis.applib.AppManifestAbstract;

import org.isisaddons.module.excel.ExcelModule;

import org.incode.domainapp.extended.module.fixtures.per_cpt.lib.excel.ExampleDomLibExcelModule;

public class ExcelLibAppManifest extends AppManifestAbstract {

    public static final Builder BUILDER = Builder.forModules(
            ExcelModule.class,
            ExampleDomLibExcelModule.class,
            ExcelAppModule.class
    );

    public ExcelLibAppManifest() {
        super(BUILDER);
    }

}