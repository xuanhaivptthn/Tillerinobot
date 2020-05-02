package org.tillerino.ppaddict.chat.waiter;

import org.tillerino.ppaddict.chat.impl.ProcessorsModule;

import dagger.Component;

@Component(modules = {
	TillerinobotConfigurationModule.class,
	ProcessorsModule.class
})
public interface WaiterComponent {
}
