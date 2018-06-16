package org.incode.domainapp.extended.appdefn.fixture.teardown;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import org.incode.domainapp.extended.module.fixtures.shared.simple.fixture.SimpleObject_tearDown;

public class DomainAppTearDown extends FixtureScript {

    @Override
    protected void execute(ExecutionContext executionContext) {
        executionContext.executeChild(this, new SimpleObject_tearDown());
    }

}
