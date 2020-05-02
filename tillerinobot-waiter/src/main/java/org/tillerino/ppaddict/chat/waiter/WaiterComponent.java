package org.tillerino.ppaddict.chat.waiter;

import javax.inject.Singleton;

import org.tillerino.ppaddict.chat.impl.ProcessorsModule;

import dagger.Component;

@Component(modules = {
	TillerinobotConfigurationModule.class,
	ProcessorsModule.class
})
@Singleton
public interface WaiterComponent {
}
