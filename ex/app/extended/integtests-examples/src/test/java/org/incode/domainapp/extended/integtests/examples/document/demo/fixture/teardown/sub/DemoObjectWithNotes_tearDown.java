package org.incode.domainapp.extended.integtests.examples.document.demo.fixture.teardown.sub;

import org.apache.isis.applib.fixturescripts.teardown.TeardownFixtureAbstract2;

import org.incode.domainapp.extended.integtests.examples.document.demo.dom.demowithnotes.DemoObjectWithNotes;

public class DemoObjectWithNotes_tearDown extends TeardownFixtureAbstract2 {

    @Override
    protected void execute(final ExecutionContext executionContext) {
        deleteFrom(DemoObjectWithNotes.class);
    }


}