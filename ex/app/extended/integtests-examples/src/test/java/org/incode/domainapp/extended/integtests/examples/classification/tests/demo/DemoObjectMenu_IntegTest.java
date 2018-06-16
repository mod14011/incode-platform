package org.incode.domainapp.extended.integtests.examples.classification.tests.demo;

import java.util.List;

import javax.inject.Inject;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.incode.domainapp.extended.integtests.examples.classification.ClassificationModuleIntegTestAbstract;
import org.incode.domainapp.extended.module.fixtures.shared.demowithatpath.dom.DemoObjectWithAtPath;
import org.incode.domainapp.extended.module.fixtures.shared.demowithatpath.dom.DemoObjectWithAtPathMenu;
import org.incode.domainapp.extended.module.fixtures.per_cpt.examples.classification.fixture.DemoObjectWithAtPath_and_OtherObjectWithAtPath_create3;

public class DemoObjectMenu_IntegTest extends ClassificationModuleIntegTestAbstract {

    DemoObjectWithAtPath_and_OtherObjectWithAtPath_create3 fs;

    @Before
    public void setUpData() throws Exception {
        fs = new DemoObjectWithAtPath_and_OtherObjectWithAtPath_create3();
        fixtureScripts.runFixtureScript(fs, null);
    }

    @Ignore // TODO: to fix...
    @Test
    public void listAll() throws Exception {

        // given
        int numDemoObjects = fs.getDemoObjects().size();

        // when
        final List<DemoObjectWithAtPath> all = wrap(demoObjectMenu).listAllDemoObjectsWithAtPath();

        // then
        Assertions.assertThat(all.size()).isEqualTo(numDemoObjects);
    }
    
    @Test
    public void create() throws Exception {

        // given
        final List<DemoObjectWithAtPath> before = wrap(demoObjectMenu).listAllDemoObjectsWithAtPath();
        int numBefore = before.size();

        // when
        wrap(demoObjectMenu).createDemoObjectWithAtPath("Faz", "/");

        // then
        final List<DemoObjectWithAtPath> after = wrap(demoObjectMenu).listAllDemoObjectsWithAtPath();
        Assertions.assertThat(after.size()).isEqualTo(numBefore+1);
    }

    @Inject
    DemoObjectWithAtPathMenu demoObjectMenu;


}