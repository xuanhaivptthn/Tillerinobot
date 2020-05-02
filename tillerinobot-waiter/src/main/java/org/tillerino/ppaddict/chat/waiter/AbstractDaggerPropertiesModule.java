package org.tillerino.ppaddict.chat.waiter;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractDaggerPropertiesModule {
	protected static boolean boolProp(Properties props, String s) {
		return Boolean.parseBoolean(stringProp(props, s));
	}

	protected static int intProp(Properties props, String s) {
		try {
			return Integer.parseInt(stringProp(props, s));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(s, e);
		}
	}

	protected static String stringProp(Properties props, String s) {
		String fromEnv = System.getenv(s.toUpperCase().replace('.', '_'));
		if (StringUtils.isNotBlank(fromEnv)) {
			return fromEnv;
		}
		String fromProps = (String) props.get(s);
		if (StringUtils.isNotBlank(fromProps)) {
			return fromProps;
		}
		throw new NoSuchElementException("Property " + s + " not set");
	}

	protected static Properties loadProps(String path) {
		Properties properties = new Properties();
		try (InputStream is = TillerinobotConfigurationModule.class.getResourceAsStream(path)) {
			if (is != null) {
				properties.load(is);
			} else {
				log.warn("Resource not found: {}", path);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return properties;
	}
}
