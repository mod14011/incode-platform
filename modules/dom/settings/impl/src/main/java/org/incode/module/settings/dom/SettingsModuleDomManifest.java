package org.incode.module.settings.dom;

import org.apache.isis.applib.AppManifestAbstract;

import org.incode.module.settings.SettingsModule;

/**
 * Provided for <tt>isis-maven-plugin</tt>.
 */
public class SettingsModuleDomManifest extends AppManifestAbstract {

    public static final Builder BUILDER = Builder.forModules(
            SettingsModule.class  // domain (entities and repositories)
    );

    public SettingsModuleDomManifest() {
        super(BUILDER);
    }

}
