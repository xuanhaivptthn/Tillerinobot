package tillerino.tillerinobot.lang;

import java.util.List;
import java.util.Random;

import org.tillerino.osuApiModel.Mods;
import org.tillerino.osuApiModel.OsuApiUser;
import org.tillerino.ppaddict.chat.GameChatResponse;

import org.tillerino.ppaddict.chat.GameChatResponse.Action;
import org.tillerino.ppaddict.chat.GameChatResponse.Message;

/**
 * TRANSLATION NOTE:
 * 
 * Please put some contact data into the following tag. If any additional
 * messages are required, I'll use the English version in all translations and
 * notify the authors.
 * 
 * @author Tillerino tillmann.gaida@gmail.com https://github.com/Tillerino https://osu.ppy.sh/u/2070907
 */
public class Default extends AbstractMutableLanguage {
	private static final long serialVersionUID = 1L;
	static final Random rnd = new Random();

	@Override
	public String unknownBeatmap() {
		return "Xin lỗi, mình không biết map này. Có thể do map quá mới, quá khó, chưa được xếp hạng, hoặc không phải osu!std.";
	}

	@Override
	public String internalException(String marker) {
		return "Ugh, có vẻ như 'ông' Tillerino lại nghịch dây nối của tôi rồi."
				+ " Nếu tên đó không biết, bạn có thể [https://github.com/Tillerino/Tilerinobot/wiki/Contact liên hệ] với hắn không?  (reference "
				+ marker + ")";
	}

	@Override
	public String externalException(String marker) {
		return "Cái quái gì vậy? Tôi đang nhận được toàn thứ linh tinh từ server của osu!. Dịch hộ tôi với 0110010001010001011101110011010001110111001110010101011101100111010110000110001101010001"
				+ " 'Ông' Tilerinno bảo rằng không phải lo gì cả, và rằng chúng ta nên thử lại một lần nữa."
				+ " Nếu bạn đang lo lắng quá, bạn có thể [https://github.com/Tillerino/Tillerinobot/wiki/Contact liên hệ] với ông ta (reference "
				+ marker + ")";
	}

	@Override
	public String noInformationForModsShort() {
		return "không có dữ liệu cho mod đang chọn";
	}

	@Override
	public GameChatResponse welcomeUser(OsuApiUser apiUser, long inactiveTime) {
		if (inactiveTime < 60 * 1000) {
			return new Message("beep boop");
		} else if (inactiveTime < 24 * 60 * 60 * 1000) {
			return new Message("Mừng trở lại, " + apiUser.getUserName() + ".");
		} else if (inactiveTime > 7l * 24 * 60 * 60 * 1000) {
			return new Message(apiUser.getUserName() + "...")
				.then(new Message("Có phải bạn không? :0 Lâu lắm mới gặp lại!"))
				.then(new Message("Thật tốt khi bạn đã trở lại. Bạn có muốn tôi gợi ý một vài beatmap không?"));
		} else {
			String[] messages = {
					"có vẻ như bạn cần sự gợi ý.",
					"thật tuyệt khi gặp bạn :D",
					"người tôi thích nhất. (Đừng nói với ai nhé!)",
					"thật là bất ngờ! ^.^",
					"tôi đợi bạn mãi. Tất cả những người khác đều kì cục, nhưng đừng có nói với họ là tôi nói thế đấy nhá! :3",
					"bạn muốn làm gì hôm nay?",
			};

			Random random = new Random();

			String message = messages[random.nextInt(messages.length)];

			return new Message(apiUser.getUserName() + ", " + message);
		}
	}

	@Override
	public String unknownCommand(String command) {
		return "Lệnh \"" + command
				+ "\" không tồn tại. Gõ !help nếu bạn cần trợ giúp!";
	}

	@Override
	public String noInformationForMods() {
		return "Xin lỗi, tôi không thể đưa cho bạn thông tin về các mod lúc này.";
	}

	@Override
	public String malformattedMods(String mods) {
		return "Những mod bạn chọn có vẻ chưa đúng. Mod có thể là bất cứ sự kết hợp nào giữa DT HR HD HT EZ NC FL SO NF. Kết hợp chúng mà không có khoảng trắng hay kí tự đặc biệt ở giữa. Ví dụ: !with HDHR, !with DTEZ";
	}

	@Override
	public String noLastSongInfo() {
		return "Tôi không nhớ đã đưa bạn bất cứ thông tin bài nào...";
	}

	@Override
	public String tryWithMods() {
		return "Hãy thử map này với một vài mod đi!";
	}

	@Override
	public String tryWithMods(List<Mods> mods) {
		return "Hãy thử map này với " + Mods.toShortNamesContinuous(mods) + "!";
	}

	@Override
	public String excuseForError() {
		return "Tôi xin lỗi, vừa mới có một hàng những số 0 và 1 đi qua làm tôi mất tập trung. Bạn cần gì vậy?";
	}

	@Override
	public String complaint() {
		return "Những khiếu nại (phàn nàn) của bạn đã được ghi lại. Tillerino sẽ xem chúng khi anh ta rảnh :P";
	}

	@Override
	public GameChatResponse hug(OsuApiUser apiUser) {
		return new Message("Come here, you!")
			.then(new Action("hugs " + apiUser.getUserName()));
	}

	@Override
	public String help() {
		return "Heyo! Tui là một con robot đã giết Tillerino và lấy acc của anh ta. Đùa đấy, nhưng tôi sử dụng tài khoản này nhiều lắm."
				+ " [https://twitter.com/Tillerinobot Tình trạng và cập nhật]"
				+ " - [https://github.com/Tillerino/Tillerinobot/wiki Lệnh]"
				+ " - [http://ppaddict.tillerino.org/ Nghiện PP?]"
				+ " - [https://github.com/Tillerino/Tillerinobot/wiki/Contact Liên hệ]";
	}

	@Override
	public String faq() {
		return "[https://github.com/Tillerino/Tillerinobot/wiki/FAQ Những câu hỏi thường gặp]";
	}

	@Override
	public String featureRankRestricted(String feature, int minRank, OsuApiUser user) {
		return "Xin lỗi, hiện tại tính năng " + feature + " chỉ có sẵn đối với người từ rank " + minRank + " trở lên.";
	}

	@Override
	public String mixedNomodAndMods() {
		return "Ý bạn là gì khi ghép \"không mod\" với \"mod\"?";
	}

	@Override
	public String outOfRecommendations() {
		return "[https://github.com/Tillerino/Tillerinobot/wiki/FAQ#the-bot-says-its-out-of-recommendations-what-do"
				+ " Tôi hết map để gợi ý cho bạn rồi :(]."
				+ " Hãy thử một vài tùy chọn gợi ý khác hoặc !reset. Nếu bạn không chắc chắn, hãy xem qua !help.";
	}

	@Override
	public String notRanked() {
		return "Beatmap chưa được xếp hạng.";
	}

	@Override
	public String invalidAccuracy(String acc) {
		return "Độ chính xác không hợp lệ: \"" + acc + "\"";
	}

	@Override
	public GameChatResponse optionalCommentOnLanguage(OsuApiUser apiUser) {
		/*
		 * TRANSLATION NOTE: This line is sent to the user right after they have
		 * chosen this Language implementation. The English version refers to
		 * itself as the default version ("just the way I am"), so translating
		 * the English message doesn't make any sense.
		 * 
		 * Instead, we've been using the line
		 * "*Translator* helped me learn *Language*." in translations. Replace
		 * *Translator* with your osu name and *Language* with the name of the
		 * language that you are translating to, and translate the line into the
		 * new language. This serves two purposes: It shows that the language
		 * was changed and gives credit to the translator.
		 * 
		 * You don't need to use the line above, and you don't have have to give
		 * yourself credit, but you should show that the language has changed.
		 * For example, in the German translation, I just used the line
		 * "Nichts leichter als das!", which translates literally to
		 * "Nothing easier than that!", which refers to German being my first
		 * language.
		 * 
		 * Tillerino
		 * 
		 * P.S. you can put a link to your profile into the line like this:
		 * [https://osu.ppy.sh/u/2070907 Tillerino]
		 */
		return new Message("[https://osu.ppy.sh/users/13456818 BCraftMG] đã dạy tôi tiếng Việt :D");
	}

	@Override
	public String invalidChoice(String invalid, String choices) {
		return "I'm sorry, but \"" + invalid
				+ "\" does not compute. Try these: " + choices + "!";
	}

	@Override
	public String setFormat() {
		return "The syntax to set a parameter is !set option value. Try !help if you need more pointers.";
	}
	
	StringShuffler apiTimeoutShuffler = new StringShuffler(rnd);
	
	@Override
	public String apiTimeoutException() {
		registerModification();
		final String message = "Server osu! lúc này đang cực kì chậm, nên tôi không thể làm gì cho bạn lúc này được :( ";
		return message + apiTimeoutShuffler.get(
				"Kể với tôi lần cuối cùng bạn trò chuyện với bà ngoại của bạn là lúc nào đi?",
				"Hay là bạn đi dọn phòng của bạn rồi hỏi lại đi? Trong lúc server osu! đang chậm chạp ấy?",
				"Dám cá là bạn muốn đi bộ một lúc. Bạn biết chứ, thế giới bên ngoài ấy?",
				"Tôi biết là bạn có rất nhiều việc khác để làm mà. Tại sao không làm chúng lúc này chứ?",
				"Đằng nào nhìn bạn cũng có vẻ buồn ngủ rồi.",
				"Trong lúc đó, hãy xem qua trang này trên [https://en.wikipedia.org/wiki/Special:Random wikipedia]!",
				"Hãy xem thử xem có ai giỏi đang [http://www.twitch.tv/directory/game/Osu! stream] ngay lúc này!",
				"Đây, một [http://dagobah.net/flash/Cursor_Invisible.swf game] mà bạn chắc chắn chơi rất tệ!",
				"Đây là một thời điểm thích hợp để bạn học qua [https://github.com/Tillerino/Tillerinobot/wiki hướng dẫn của tôi].",
				"Đừng lo, mấy cái [https://www.reddit.com/r/osugame dank memes] sẽ giúp bạn trong lúc đợi.",
				"Khi bạn đang chán, hãy thử [http://gabrielecirulli.github.io/2048/ 2048]!",
				"Câu hỏi vui: Nếu ổ cứng của bạn chết ngay lúc này, bao nhiêu dữ liệu cá nhân của bạn sẽ biến mất mãi mãi?",
				"Thế... bạn đã bao giờ thử [https://www.google.com/search?q=bring%20sally%20up%20push%20up%20challenge sally up push up challenge] chưa?",
				"Bạn có thể đi làm điều gì khác hoặc chúng ta cũng có thể nhìn chằm chằm vào mắt nhau. Trong im lặng."
				);
	}

	@Override
	public String noRecentPlays() {
		return "Mình không thấy lượt bạn chơi gần đây :(";
	}
	
	@Override
	public String isSetId() {
		return "This references a set of beatmaps, not a single beatmap.";
	}
}
