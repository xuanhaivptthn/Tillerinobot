package org.tillerino.ppaddict.chat.waiter;

import java.util.Properties;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TillerinobotConfigurationModule extends AbstractDaggerPropertiesModule {
	@Provides
	@Named("tillerinobot.irc.server")
	public static String ircServer(@Named("irc.file.props") Properties props) {
		return stringProp(props, "tillerinobot.irc.server");
	}

	@Provides
	@Named("tillerinobot.irc.port")
	public static int ircPort(@Named("irc.file.props") Properties props) {
		return intProp(props, "tillerinobot.irc.port");
	}

	@Provides
	@Named("tillerinobot.irc.nickname")
	public static String ircNick(@Named("irc.file.props") Properties props) {
		return stringProp(props, "tillerinobot.irc.nickname");
	}

	@Provides
	@Named("tillerinobot.irc.password")
	public static String ircPass(@Named("irc.file.props") Properties props) {
		return stringProp(props, "tillerinobot.irc.password");
	}

	@Provides
	@Named("tillerinobot.irc.autojoin")
	public static String ircAutoJoin(@Named("irc.file.props") Properties props) {
		return stringProp(props, "tillerinobot.irc.autojoin");
	}

	@Provides
	@Named("tillerinobot.ignore")
	public static boolean ircIgnore(@Named("irc.file.props") Properties props) {
		return boolProp(props, "tillerinobot.ignore");
	}

	@Provides
	@Named("irc.file.props")
	@Singleton
	public static Properties props() {
		Properties properties = new Properties();
		properties.putAll(System.getProperties());
		properties.putAll(loadProps("/tillerinobot.properties"));
		return properties;
	}
}
