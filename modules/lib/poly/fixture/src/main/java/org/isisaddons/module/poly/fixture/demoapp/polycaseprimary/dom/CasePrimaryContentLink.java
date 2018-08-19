package org.isisaddons.module.poly.fixture.demoapp.polycaseprimary.dom;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.NotPersistent;

import com.google.common.base.Function;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Programmatic;

import org.isisaddons.module.poly.dom.PolymorphicAssociationLink;
import org.isisaddons.module.poly.fixture.demoapp.demomodule.dom.casemgmt.Case;
import org.isisaddons.module.poly.fixture.demoapp.polycasecontent.dom.content.CaseContent;

@javax.jdo.annotations.PersistenceCapable(
        identityType=IdentityType.DATASTORE,
        schema = "fixtureLibPoly"
)
@javax.jdo.annotations.DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Inheritance(
        strategy = InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "findByCase", language = "JDOQL",
                value = "SELECT "
                        + "FROM org.incode.domainapp.extended.module.fixtures.per_cpt.lib.poly.dom.poly.caseprimary.CasePrimaryContentLink "
                        + "WHERE case == :case"),
        @javax.jdo.annotations.Query(
                name = "findByContent", language = "JDOQL",
                value = "SELECT "
                        + "FROM org.incode.domainapp.extended.module.fixtures.per_cpt.lib.poly.dom.poly.caseprimary.CasePrimaryContentLink "
                        + "WHERE contentObjectType == :contentObjectType "
                        + "   && contentIdentifier == :contentIdentifier "),
        @javax.jdo.annotations.Query(
                name = "findByCaseAndContent", language = "JDOQL",
                value = "SELECT "
                        + "FROM org.incode.domainapp.extended.module.fixtures.per_cpt.lib.poly.dom.poly.caseprimary.CasePrimaryContentLink "
                        + "WHERE case == :case "
                        + "   && contentObjectType == :contentObjectType "
                        + "   && contentIdentifier == :contentIdentifier ")
})
@javax.jdo.annotations.Unique(name="CasePrimaryContentLink_case_content_UNQ", members = {"case","contentObjectType","contentIdentifier"})
@DomainObject
public abstract class CasePrimaryContentLink extends PolymorphicAssociationLink<Case, CaseContent, CasePrimaryContentLink> {

    public static class InstantiateEvent
            extends PolymorphicAssociationLink.InstantiateEvent<Case, CaseContent, CasePrimaryContentLink> {

        public InstantiateEvent(final Object source, final Case aCase, final CaseContent content) {
            super(CasePrimaryContentLink.class, source, aCase, content);
        }
    }

    //region > constructor
    public CasePrimaryContentLink() {
        super("{subject} primary {polymorphicReference}");
    }
    //endregion

    //region > SubjectPolymorphicReferenceLink API
    @Override
    @Programmatic
    public Case getSubject() {
        return getCase();
    }

    @Override
    @Programmatic
    public void setSubject(final Case subject) {
        setCase(subject);
    }

    @Override
    @Programmatic
    public String getPolymorphicObjectType() {
        return getContentObjectType();
    }

    @Override
    @Programmatic
    public void setPolymorphicObjectType(final String polymorphicObjectType) {
        setContentObjectType(polymorphicObjectType);
    }

    @Override
    @Programmatic
    public String getPolymorphicIdentifier() {
        return getContentIdentifier();
    }

    @Override
    @Programmatic
    public void setPolymorphicIdentifier(final String polymorphicIdentifier) {
        setContentIdentifier(polymorphicIdentifier);
    }
    //endregion

    //region > case (property)
    @NotPersistent // because we (have to) have a non-standard name for this field
    private Case _case;
    @Column(
            allowsNull = "false",
            name = "case_id"
    )
    public Case getCase() {
        return _case;
    }

    public void setCase(final Case aCase) {
        this._case = aCase;
    }
    //endregion

    //region > contentObjectType (property)
    private String contentObjectType;

    @Column(allowsNull = "false", length = 255)
    public String getContentObjectType() {
        return contentObjectType;
    }

    public void setContentObjectType(final String contentObjectType) {
        this.contentObjectType = contentObjectType;
    }
    //endregion

    //region > contentIdentifier (property)
    private String contentIdentifier;

    @Column(allowsNull = "false", length = 255)
    public String getContentIdentifier() {
        return contentIdentifier;
    }

    public void setContentIdentifier(final String contentIdentifier) {
        this.contentIdentifier = contentIdentifier;
    }
    //endregion

    public static class Functions {
        public static Function<CasePrimaryContentLink, Case> GET_CASE = new Function<CasePrimaryContentLink, Case>() {
            @Override
            public Case apply(final CasePrimaryContentLink input) {
                return input.getSubject();
            }
        };
        public static Function<CasePrimaryContentLink, CaseContent> GET_CONTENT = new Function<CasePrimaryContentLink, CaseContent>() {
            @Override
            public CaseContent apply(final CasePrimaryContentLink input) {
                return input.getPolymorphicReference();
            }
        };
    }

}
