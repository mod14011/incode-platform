package org.incode.domainapp.extended.integtests.examples.tags.tests;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.jdo.Query;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

import org.incode.domainapp.extended.integtests.examples.tags.TagsModuleIntegTestAbstract;
import org.incode.example.alias.demo.examples.tags.dom.demo.DemoTaggableObject;
import org.incode.example.alias.demo.examples.tags.dom.demo.DemoTaggableObjectMenu;
import org.incode.example.alias.demo.examples.tags.fixture.DemoTaggableObject_withTags_create3;
import org.incode.example.tags.dom.impl.Tag;
import org.incode.example.tags.dom.impl.Tags;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class Tags_IntegTest extends TagsModuleIntegTestAbstract {

    DemoTaggableObject entity;

    @Before
    public void setUpData() throws Exception {
        runFixtureScript(new DemoTaggableObject_withTags_create3());
    }

    @Inject
    DemoTaggableObjectMenu exampleTaggableEntities;

    @Inject
    Tags tags;

    @Inject
    IsisJdoSupport isisJdoSupport;

    @Before
    public void setUp() throws Exception {
        final List<DemoTaggableObject> all = wrap(exampleTaggableEntities).listAllTaggableObjects();
        assertThat(all.size(), is(4));

        entity = all.get(0);
        assertThat(entity.getBrand(), is("Coca Cola"));
        assertThat(entity.getSector(), is("Drink"));
    }

    public static class TagsFor extends Tags_IntegTest {

        @Test
        public void whenOthersUseTheTag() throws Exception {

            // given
            assertThat(entity.choicesSector(), containsInAnyOrder("Drink", "Fast food", "Clothing"));
            assertThat(uniqueValuesOf(tagsWithKey("Sector")), hasSize(3));
            assertThat(entity.getSector(), is("Drink")); // 2 using Drink

            // when
            entity.setSector("Fast food");
            transactionService.nextTransaction();

            // then updated...
            assertThat(entity.getSector(), is("Fast food"));
            // but no tags removed
            assertThat(entity.choicesSector(), containsInAnyOrder("Drink", "Fast food", "Clothing"));
            assertThat(uniqueValuesOf(tagsWithKey("Sector")), hasSize(3));
        }

        @Ignore("hmmm... does not seem to remove.")
        @Test
        public void whenNoOthersUseTheTag() throws Exception {

            // given
            assertThat(entity.choicesBrand(), containsInAnyOrder("Coca Cola", "Levi's", "McDonalds", "Pepsi"));
            assertThat(uniqueValuesOf(tagsWithKey("Brand")), hasSize(4));

            // when
            entity.setBrand("Pepsi");
            transactionService.nextTransaction();

            // then updated...
            assertThat(entity.getBrand(), is("Pepsi"));
            // then tag removed
            final List<String> actual = entity.choicesBrand();
            assertThat(actual, containsInAnyOrder("Levi's", "McDonalds", "Coca Cola"));
            assertThat(uniqueValuesOf(tagsWithKey("Brand")), hasSize(3));
        }

        private List<Tag> tagsWithKey(String key) {
            final Query query = isisJdoSupport.getJdoPersistenceManager().newQuery("SELECT FROM " + Tag.class.getName() + " where key == :key");
            return (List<Tag>) query.execute(key);
        }

        private static Set<String> uniqueValuesOf(List<Tag> tags) {
            return Sets.newTreeSet(Iterables.transform(tags, Tag.Functions.GET_VALUE));
        }

    }



}