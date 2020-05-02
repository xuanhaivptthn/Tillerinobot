package org.tillerino.ppaddict.chat.impl;

import javax.inject.Named;

import org.tillerino.ppaddict.chat.GameChatEventConsumer;
import org.tillerino.ppaddict.chat.GameChatResponseConsumer;

import dagger.Module;
import dagger.Provides;

/**
 * Binds {@link MessagePreprocessor} and {@link ResponsePostprocessor}.
 */
@Module
public class ProcessorsModule {
	@Provides
	@Named("messagePreprocessor")
	public static GameChatEventConsumer pre(MessagePreprocessor pre) {
		return pre;
	}

	@Provides
	@Named("responsePostprocessor")
	public static GameChatResponseConsumer post(ResponsePostprocessor post) {
		return post;
	}
}
