package entity;

import com.google.gson.*;

import java.lang.reflect.Type;

public class AbstractCarriageAdapter implements JsonSerializer<AbstractCarriage>,
		JsonDeserializer<AbstractCarriage> {

	@Override
	public JsonElement serialize(AbstractCarriage src, Type typeOfSrc,
								 JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
		result.add("properties", context.serialize(src, src.getClass()));
		return result;
	}

	@Override
	public AbstractCarriage deserialize(JsonElement jsonElement,
										Type type,
										JsonDeserializationContext jsonDeserializationContext)
			throws JsonParseException {

		JsonObject jsonObject = jsonElement.getAsJsonObject();
		String typeOfSrc = jsonObject.get("type").getAsString();
		JsonElement element = jsonObject.get("properties");

		try {
			String thePackage = "entity.";
			return jsonDeserializationContext.deserialize(element,
					Class.forName(thePackage + typeOfSrc));
		} catch (ClassNotFoundException cnfe) {
			throw new JsonParseException("Unknown element type: " + typeOfSrc, cnfe);
		}
	}
}
