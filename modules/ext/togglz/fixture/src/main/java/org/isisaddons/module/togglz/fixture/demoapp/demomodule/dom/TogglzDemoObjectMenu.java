package org.isisaddons.module.togglz.fixture.demoapp.demomodule.dom;

import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.SemanticsOf;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "extTogglzFixture.TogglzDemoObjectMenu"
)
@DomainServiceLayout(
        named = "Demo",
        menuOrder = "10.1"
)
public class TogglzDemoObjectMenu {


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "1")
    public List<TogglzDemoObject> listAllDemoObjects() {
        return repository.listAll();
    }


    @MemberOrder(sequence = "2")
    public TogglzDemoObject createDemoObject(final String name) {
        return repository.create(name);
    }

    @MemberOrder(sequence = "3")
    public List<TogglzDemoObject> findDemoObjectByName(final String name) {
        return repository.findByName(name);
    }

    @javax.inject.Inject
    TogglzDemoObjectRepository repository;


}
