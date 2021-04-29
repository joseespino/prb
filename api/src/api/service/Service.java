package api.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import api.model.Response;

public class Service {

	private static final JsonParser parser = new JsonParser();

	public static ArrayList<Response> get(String planet) throws Exception {
		String json = readUrl(
				"https://api.nasa.gov/neo/rest/v1/feed?start_date=2020-09-09&end_date=2020-09-09&api_key=DEMO_KEY");
		JsonElement neoJsonElement = parser.parse(json);
		return getPotentiallyHazardousAsteroid(neoJsonElement, planet);

	}

	private static ArrayList<Response> getPotentiallyHazardousAsteroid(JsonElement neoJsonElement, String planet) {
		ArrayList<Response> arrayResponse = new ArrayList<Response>();
		JsonElement nearEarthObjects = neoJsonElement.getAsJsonObject().get("near_earth_objects");
		for (Map.Entry<String, JsonElement> neoClosestApproachDate : nearEarthObjects.getAsJsonObject().entrySet()) {
			for (JsonElement neo : neoClosestApproachDate.getValue().getAsJsonArray()) {
				if (neo.getAsJsonObject().get("is_potentially_hazardous_asteroid").getAsBoolean()) {
					Response response = new Response();
					response.setNombre(planet);
					response.setDiametro((neo.getAsJsonObject().get("estimated_diameter").getAsJsonObject()
							.get("kilometers").getAsJsonObject().get("estimated_diameter_min").getAsFloat()
							+ neo.getAsJsonObject().get("estimated_diameter").getAsJsonObject().get("kilometers")
									.getAsJsonObject().get("estimated_diameter_max").getAsFloat())
							/ 2);
					response.setFecha(neo.getAsJsonObject().get("close_approach_data").getAsJsonArray().get(0)
							.getAsJsonObject().get("close_approach_date").toString());
					response.setVelocidad(neo.getAsJsonObject().get("close_approach_data").getAsJsonArray().get(0)
							.getAsJsonObject().get("relative_velocity").getAsJsonObject().get("kilometers_per_second")
							.toString());
					response.setPlaneta(neo.getAsJsonObject().get("close_approach_data").getAsJsonArray().get(0)
							.getAsJsonObject().get("orbiting_body").toString());
					arrayResponse.add(response);
				}
			}
		}
		return arrayResponse;
	}
	
	
	private static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

}
