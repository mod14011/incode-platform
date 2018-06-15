package org.incode.domainapp.extended.module.fixtures.per_cpt.examples.communications.fixture.data.doctypes;

import org.incode.example.docrendering.freemarker.fixture.RenderingStrategyFSForFreemarker;
import org.incode.example.document.fixture.DocumentTemplateFSAbstract;

public class RenderingStrategy_create1 extends DocumentTemplateFSAbstract {

    public static final String REF_FMK = RenderingStrategyFSForFreemarker.REF;


    @Override
    protected void execute(final ExecutionContext executionContext) {

        // prereqs
        executionContext.executeChild(this, new RenderingStrategyFSForFreemarker());

    }


}