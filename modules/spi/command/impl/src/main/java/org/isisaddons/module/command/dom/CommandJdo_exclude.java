package org.isisaddons.module.command.dom;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Mixin;
import org.apache.isis.applib.annotation.SemanticsOf;

import org.isisaddons.module.command.CommandModule;

@Mixin(method = "act")
public class CommandJdo_exclude<T> {

    private final CommandJdo commandJdo;

    //region > constructor
    public CommandJdo_exclude(CommandJdo commandJdo) {
        this.commandJdo = commandJdo;
    }
    //endregion


    public static class ActionDomainEvent extends CommandModule.ActionDomainEvent<CommandJdo_exclude> { }
    @Action(
            semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE,
            domainEvent = ActionDomainEvent.class
    )
    @ActionLayout(
            contributed = Contributed.AS_ACTION
    )
    @MemberOrder(name = "exception", sequence = "2")
    public CommandJdo act() {

        commandJdo.setReplayState(ReplayState.EXCLUDED);
        return commandJdo;
    }

    public boolean hideAct() {
        return commandJdo.getReplayState() == null;
    }
    public String disableAct() {
        final boolean notInError =
                commandJdo.getReplayState() == null || !commandJdo.getReplayState().representsError();
        return notInError
                ? "This command cannot be excluded."
                : null;
    }

}
