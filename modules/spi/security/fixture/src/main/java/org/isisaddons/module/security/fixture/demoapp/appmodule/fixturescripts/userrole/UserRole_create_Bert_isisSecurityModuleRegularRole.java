package org.isisaddons.module.security.fixture.demoapp.appmodule.fixturescripts.userrole;

import org.isisaddons.module.security.fixture.demoapp.appmodule.fixturescripts.users.ApplicationUser_create_Bert_in_Italy;
import org.isisaddons.module.security.seed.scripts.IsisModuleSecurityRegularUserRoleAndPermissions;

public class UserRole_create_Bert_isisSecurityModuleRegularRole extends AbstractUserRoleFixtureScript {
    public UserRole_create_Bert_isisSecurityModuleRegularRole() {
        super(ApplicationUser_create_Bert_in_Italy.USER_NAME, IsisModuleSecurityRegularUserRoleAndPermissions.ROLE_NAME);
    }
}
