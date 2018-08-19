package org.isisaddons.module.security.integtests.tests.role;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import org.isisaddons.module.security.dom.role.ApplicationRole;
import org.isisaddons.module.security.dom.role.ApplicationRoleRepository;
import org.isisaddons.module.security.fixture.demoapp.appmodule.fixturescripts.SecurityModuleAppTearDown;
import org.isisaddons.module.security.integtests.SecurityModuleIntegTestAbstract;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ApplicationRoleRepository_IntegTest extends SecurityModuleIntegTestAbstract {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUpData() throws Exception {
        runFixtureScript(new SecurityModuleAppTearDown());
    }

    @Inject
    ApplicationRoleRepository applicationRoleRepository;


    public static class NewRole extends ApplicationRoleRepository_IntegTest {

        @Test
        public void happyCase() throws Exception {

            // given
            final List<ApplicationRole> before = applicationRoleRepository.allRoles();
            assertThat(before.size(), is(0));

            // when
            final ApplicationRole applicationRole = applicationRoleRepository.newRole("fred", null);
            assertThat(applicationRole.getName(), is("fred"));

            // then
            final List<ApplicationRole> after = applicationRoleRepository.allRoles();
            assertThat(after.size(), is(1));
        }

        @Test
        public void alreadyExists() throws Exception {
            // given
            applicationRoleRepository.newRole("guest", null);

            // when
            applicationRoleRepository.newRole("guest", null);
            
            // then
            assertThat(applicationRoleRepository.allRoles().size(), is(1));
        }

    }

    public static class FindByName extends ApplicationRoleRepository_IntegTest {

        @Before
        public void setUpData() throws Exception {
            runFixtureScript(new SecurityModuleAppTearDown());
        }

        @Test
        public void happyCase() throws Exception {

            // given
            applicationRoleRepository.newRole("guest", null);
            applicationRoleRepository.newRole("root", null);

            // when
            sessionManagementService.nextSession();
            final ApplicationRole guest = applicationRoleRepository.findByNameCached("guest");

            // then
            assertThat(guest, is(not(nullValue())));
            assertThat(guest.getName(), is("guest"));
        }

        @Test
        public void whenDoesntMatch() throws Exception {

            // given
            applicationRoleRepository.newRole("guest", null);
            applicationRoleRepository.newRole("root", null);
            sessionManagementService.nextSession();

            // when
            final ApplicationRole nonExistent = applicationRoleRepository.findByNameCached("admin");

            // then
            assertThat(nonExistent, is(nullValue()));
        }
    }

    public static class Find extends ApplicationRoleRepository_IntegTest {

        @Before
        public void setUpData() throws Exception {
            runFixtureScript(new SecurityModuleAppTearDown());
        }

        @Test
        public void happyCase() throws Exception {

            // given
            applicationRoleRepository.newRole("guest", null);
            applicationRoleRepository.newRole("root", null);

            // when
            sessionManagementService.nextSession();
            final List<ApplicationRole> result = applicationRoleRepository.findNameContaining("t");

            // then
            assertThat(result.size(), is(2));
            //assertThat(guest.getName(), is("guest"));
        }

        @Test
        public void whenDoesntMatch() throws Exception {

            // given
            applicationRoleRepository.newRole("guest", null);
            applicationRoleRepository.newRole("root", null);
            sessionManagementService.nextSession();

            // when
            final List<ApplicationRole> result = applicationRoleRepository.findNameContaining("a");

            // then
            assertThat(result.size(), is(0));
            //assertThat(guest.getName(), is("guest"));
        }
    }


    public static class AllTenancies extends ApplicationRoleRepository_IntegTest {

        @Test
        public void happyCase() throws Exception {

            // given
            applicationRoleRepository.newRole("guest", null);
            applicationRoleRepository.newRole("root", null);

            // when
            final List<ApplicationRole> after = applicationRoleRepository.allRoles();

            // then
            assertThat(after.size(), is(2));
        }
    }


}