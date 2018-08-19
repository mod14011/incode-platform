package org.isisaddons.wicket.wickedcharts.fixture.demoapp.todomodule.fixturescripts;

import org.apache.isis.applib.fixturescripts.teardown.TeardownFixtureAbstract2;

import org.isisaddons.wicket.wickedcharts.fixture.demoapp.todomodule.dom.WicketChartsDemoToDoItem;

public class WicketChartsDemoToDoItem_tearDown extends TeardownFixtureAbstract2 {

    @Override
    protected void execute(final ExecutionContext executionContext) {
        deleteFrom(WicketChartsDemoToDoItem.class);
    }

}
