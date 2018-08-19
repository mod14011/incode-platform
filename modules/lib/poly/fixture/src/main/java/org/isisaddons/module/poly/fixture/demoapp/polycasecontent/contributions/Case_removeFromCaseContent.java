package org.isisaddons.module.poly.fixture.demoapp.polycasecontent.contributions;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.Mixin;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;

import org.isisaddons.module.poly.fixture.demoapp.demomodule.dom.casemgmt.Case;
import org.isisaddons.module.poly.fixture.demoapp.demomodule.dom.casemgmt.Cases;
import org.isisaddons.module.poly.fixture.demoapp.polycasecontent.dom.content.CaseContent;
import org.isisaddons.module.poly.fixture.demoapp.polycasecontent.dom.contentlink.CaseContentLink;
import org.isisaddons.module.poly.fixture.demoapp.polycasecontent.dom.contentlink.CaseContentLinks;

@Mixin(method = "act")
public class Case_removeFromCaseContent {

    private final Case aCase;

    public Case_removeFromCaseContent(Case aCase) {
        this.aCase = aCase;
    }

    public static class RemoveDomainEvent extends ActionDomainEvent<Case> {
        public CaseContent getContent() {
            return (CaseContent) getArguments().get(0);
        }
        public Case getCase() {
            return super.getSource();
        }
    }

    @Action(domainEvent = RemoveDomainEvent.class)
    public Case act(final CaseContent caseContent) {
        caseContentLinks.findByCase(aCase)
                .stream().filter(x -> x.getPolymorphicReference() == caseContent)
                .forEach(caseContentLink -> container.removeIfNotAlready(caseContentLink));
        return aCase;
    }

    public String disableAct() {
        return choices0Act().isEmpty()? "Case has no contents": null;
    }

    public List<CaseContent> choices0Act() {
        return caseContentLinks.findByCase(aCase)
                .stream()
                .map(CaseContentLink::getPolymorphicReference)
                .collect(Collectors.toList());
    }



    @Inject
    CaseContentLinks caseContentLinks;
    @Inject
    Cases cases;
    @Inject
    DomainObjectContainer container;

}
