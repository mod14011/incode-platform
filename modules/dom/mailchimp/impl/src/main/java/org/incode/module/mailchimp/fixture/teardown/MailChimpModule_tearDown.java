package org.incode.module.mailchimp.fixture.teardown;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

public class MailChimpModule_tearDown extends FixtureScript {

    @Override
    protected void execute(ExecutionContext executionContext) {
        isisJdoSupport.executeUpdate("delete from \"incodeMailchimp\".\"MailChimpListMemberLink\"");
        isisJdoSupport.executeUpdate("delete from \"incodeMailchimp\".\"MailChimpMember\"");
        isisJdoSupport.executeUpdate("delete from \"incodeMailchimp\".\"MailChimpList\"");
    }


    @javax.inject.Inject
    private IsisJdoSupport isisJdoSupport;

}
