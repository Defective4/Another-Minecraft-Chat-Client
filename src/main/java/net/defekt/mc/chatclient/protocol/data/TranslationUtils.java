package net.defekt.mc.chatclient.protocol.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.defekt.mc.chatclient.protocol.ProtocolNumber;
import net.defekt.mc.chatclient.protocol.packets.PacketFactory;
import net.defekt.mc.chatclient.ui.Main;
import net.defekt.mc.chatclient.ui.UserPreferences;
import net.defekt.mc.chatclient.ui.UserPreferences.Language;

/**
 * This class contains translation keys used by Minecraft chat messages.<br>
 * They are used in parsing of chat messages
 * 
 * @see ChatMessages
 * @author Defective4
 *
 */

public class TranslationUtils {
	private TranslationUtils() {
	}

	private static final Map<Language, Map<String, String>> translationKeys = new HashMap<UserPreferences.Language, Map<String, String>>() {
		{
			for (final Language lang : Language.values()) {
				final InputStream is = TranslationUtils.class
						.getResourceAsStream("/resources/lang/minecraft/" + lang.getCode().toLowerCase() + ".lang");
				if (is == null) {
					continue;
				}
				final Map<String, String> kMap = new HashMap<String, String>();
				try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
					String line;
					while ((line = br.readLine()) != null)
						if (line.contains("=") && line.split("=").length > 1) {
							final String[] ags = line.split("=");
							kMap.put(ags[0], ags[1]);
						}

					br.close();
				} catch (final Exception e) {
					e.printStackTrace();
				}
				put(lang, kMap);
			}
		}
	};

	private static final Map<Integer, Map<Integer, ItemInfo>> items = new HashMap<Integer, Map<Integer, ItemInfo>>() {
		{
			final List<Integer> protocols = new ArrayList<Integer>();
			final Map<Integer, Integer> protocolBinds = PacketFactory.getProtocolBinds();
			for (final ProtocolNumber protocol : ProtocolNumber.values()) {
				int protocolNum = protocol.protocol;
				if (protocolBinds.containsKey(protocolNum)) {
					protocolNum = protocolBinds.get(protocolNum);
				}
				if (!protocols.contains(protocolNum)) {
					protocols.add(protocolNum);
				}
			}

			for (final int protocol : protocols) {
				final ProtocolNumber pNum = ProtocolNumber.getForNumber(protocol);
				try {
					final HashMap<Integer, ItemInfo> infs = new HashMap<>();
					final String pName = pNum.name;
					if (TranslationUtils.class.getResourceAsStream("/resources/items/" + pName + ".json") == null) {
						continue;
					}
					final JsonArray el = new JsonParser()
							.parse(new InputStreamReader(
									TranslationUtils.class.getResourceAsStream("/resources/items/" + pName + ".json")))
							.getAsJsonArray();
					for (final JsonElement elem : el) {
						final JsonObject job = elem.getAsJsonObject();
						final JsonObject items = job.get("items").getAsJsonObject().get("item").getAsJsonObject();
						for (final Entry<String, JsonElement> item : items.entrySet()) {
							final String itemS = item.getKey();
							final JsonObject itemData = item.getValue().getAsJsonObject();
							final int itemID = itemData.get("numeric_id").getAsInt();
							String itemName = itemS;
							if (itemData.has("name")) {
								itemName = translateKey("item." + itemData.get("name").getAsString() + ".name");
								if (itemName.equals(itemData.get("name").getAsString())
										&& itemData.has("display_name")) {
									itemName = itemData.get("display_name").getAsString();
								}
							} else if (itemData.has("display_name")) {
								itemName = itemData.get("display_name").getAsString();
							}
							infs.put(itemID, new ItemInfo(itemName, itemS));
						}
					}
					put(protocol, infs);

				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		}
	};

	/**
	 * Get an item info from its id and protocol
	 * 
	 * @param id       item's ID
	 * @param protocol protocol of this item
	 * @return item information
	 */
	public static ItemInfo getItemForID(final int id, int protocol) {
		if (PacketFactory.getProtocolBinds().containsKey(protocol)) {
			protocol = PacketFactory.getProtocolBinds().get(protocol);
		}
		final ItemInfo none = new ItemInfo("" + id, "" + id);

		if (items.containsKey(protocol)) {
			final Map<Integer, ItemInfo> itemMap = items.get(protocol);
			return itemMap.containsKey(id) ? itemMap.get(id) : none;
		}
		return none;
	}

	/**
	 * Translate a key
	 * 
	 * @param key key
	 * @return translated string
	 */
	public static String translateKey(final String key) {
		final Language lang = translationKeys.containsKey(Main.up.getAppLanguage()) ? Main.up.getAppLanguage()
				: Language.English;
		final Map<String, String> kMap = translationKeys.get(lang);
		if (kMap.containsKey(key))
			return kMap.get(key).replace("%s", "\u00A7%s");
		return key;
	}
}
