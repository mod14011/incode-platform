package org.incode.example.alias.demo.shared.fixture;

import org.apache.isis.applib.fixturescripts.teardown.TeardownFixtureAbstract2;

import org.incode.example.alias.demo.shared.dom.DemoObject;

public class DemoObject_tearDown extends TeardownFixtureAbstract2 {

    @Override
    protected void execute(final ExecutionContext executionContext) {
        deleteFrom(DemoObject.class);
    }

}
