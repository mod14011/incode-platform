package org.incode.example.alias.demo.shared.fixture;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import org.incode.example.alias.demo.shared.dom.DemoObject;
import org.incode.example.alias.demo.shared.dom.DemoObjectMenu;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class DemoObject_create extends FixtureScript {

    @Getter @Setter
    private String name;

    /**
     * The created simple object (output).
     */
    @Getter
    private DemoObject demoObject;


    @Override
    protected void execute(final ExecutionContext ec) {

        String name = checkParam("name", ec, String.class);

        this.demoObject = wrap(demoObjectMenu).createDemoObject(name);
        ec.addResult(this, demoObject);
    }

    @javax.inject.Inject
    DemoObjectMenu demoObjectMenu;

}
