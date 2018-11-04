package fr.dawan.cultureEvents.tools;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;

import fr.dawan.cultureEvents.beans.Event;

public class JsonTools {

	public static String BASE_WS_URL = "https://opendata.lillemetropole.fr/";
	
	public static List<Event> importAllEventsFromJson(String tag) throws Exception {
		//String link = BASE_WS_URL + "api/records/1.0/search/?dataset=evenements-publics-openagenda&facet=tags&facet=placename&facet=department&facet=region&facet=city&facet=date_start&facet=date_end&facet=pricing_info&facet=updated_at&facet=city_district";
		//String link = BASE_WS_URL + "/api/records/1.0/search/?dataset=evenements-publics-openagenda&rows=50&sort=date_start&facet=tags&facet=placename&facet=department&facet=region&facet=city&facet=date_start&facet=date_end&facet=pricing_info&facet=updated_at&facet=city_district";
		String link = BASE_WS_URL + "/api/records/1.0/search/?dataset=evenements-publics-openagenda&rows=50&sort=date_start&facet=tags&facet=placename&facet=department&facet=region&facet=city&facet=date_start&facet=date_end&facet=pricing_info&facet=updated_at&facet=city_district";
		if(tag!=null)
			link+="&refine.tags="+tag;
		
		Client client = ClientBuilder.newClient().register(JacksonFeature.class);
		

		Response res = client.target(link).request().get();

        //verification de la connexion - s'il y a des erreurs d'accès a la bdd - sans erreur:par défaut les codes 200+201
		if (res.getStatus() != 200 && res.getStatus() != 201) {
			throw new RuntimeException("Erreur : " + res.getStatus() + " " + res.getStatusInfo().toString());
		}

		String json = res.readEntity(String.class); //récupération du fichier JSON dans un objet String

		InputStream is = new ByteArrayInputStream(json.getBytes()); // on converrit la chaine dans un tableau bytes et on le mets dans le InputStream
		JsonReader reader = Json.createReader(is); //on garde le fichier JSON dans l'objet JsonReader = reader
		is.close();
		client.close();// fermer la connexion

		//avec le jsonreader on crée le JsonObject 
		//penser à fermer le reader
		JsonObject objGlobal = reader.readObject();
//reader.close();
		JsonArray jsonRecords = objGlobal.getJsonArray("records"); //on récupère le JSON dans un liste- en objet JsonArray=list

		//on crée la liste des event qu'on a récupéré du JSON
		List<Event> evtList = new ArrayList<Event>();
//on parcourt la liste JSON
		for (int i = 0; i < jsonRecords.size(); i++) {
			JsonObject objRecord = jsonRecords.getJsonObject(i); //on garde chaque élément de la liste en objRecord
			Event evt = constructEvent(objRecord);
			evtList.add(evt); //add des objets event dans la liste  
		}

		return evtList;

	}
	
	
	
	public static Event findEventByIdFromWS(Integer eventId) throws Exception {
		String link = BASE_WS_URL + "/api/records/1.0/search/?dataset=evenements-publics-openagenda&facet=tags&facet=placename&facet=department&facet=region&facet=city&facet=date_start&facet=date_end&facet=pricing_info&facet=updated_at&facet=city_district&refine.uid="+eventId; 
		Client client = ClientBuilder.newClient().register(JacksonFeature.class);
		

		Response res = client.target(link).request().get();
		System.out.println("res = " + res);

        //verification de la connexion - s'il y a des erreurs d'accès a la bdd - sans erreur:par défaut les codes 200+201
		if (res.getStatus() != 200 && res.getStatus() != 201) {
			throw new RuntimeException("Erreur : " + res.getStatus() + " " + res.getStatusInfo().toString());
		}

		String json = res.readEntity(String.class); //récupération du fichier JSON dans un objet String

		InputStream is = new ByteArrayInputStream(json.getBytes()); // on converrit la chaine dans un tableau bytes et on le mets dans le InputStream
		JsonReader reader = Json.createReader(is); //on garde le fichier JSON dans l'objet JsonReader = reader
		is.close();
		client.close();// fermer la connexion

		//avec le jsonreader on crée le JsonObject 
		//penser à fermer le reader
		JsonObject objGlobal = reader.readObject();
		//reader.close();
		JsonArray jsonRecords = objGlobal.getJsonArray("records"); //on récupère le JSON dans un liste- en objet JsonArray=list

		//on crée la liste des event qu'on a récupéré du JSON
		List<Event> evtList = new ArrayList<Event>();

		JsonObject objRecord = jsonRecords.getJsonObject(0); //on garde chaque élément de la liste en objRecord
		Event evt = constructEvent(objRecord);
		return evt;

	}
	
	private static Event constructEvent(JsonObject objRecord) {
		//on crée un evenements de type Event
		Event evt = new Event();

	
		JsonObject objRFields = objRecord.getJsonObject("fields"); //on retient le champ "fields"de la BDD - qui a les attributs de l'event, dans l'objet Json objRFields

		try {
			JsonArray obj0LatLon = objRFields.getJsonArray("latlon");
			evt.setLatitude(Double.parseDouble(obj0LatLon.get(0).toString()));
			evt.setLongitude(Double.parseDouble(obj0LatLon.get(1).toString()));
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		try {
			evt.setTitle(objRFields.getString("title"));// "title":"Quartier Saint Pierre - Les fêtes estivales",
		} catch (Exception ex) {
			evt.setTitle("");
		}

		try {
			evt.setLang(objRFields.getString("lang"));
		} catch (Exception ex) {
			evt.setLang("");
		}

		try {
			evt.setCity(objRFields.getString("city"));
		} catch (Exception ex) {
			evt.setCity("");
		}

		try {
			SimpleDateFormat convertDates = new SimpleDateFormat("yyyy-MM-dd");
			evt.setDateStart(convertDates.parse(objRFields.getString("date_start")));
			evt.setDateEnd(convertDates.parse(objRFields.getString("date_end")));
		} catch (Exception ex) {
              
		}

		try {
			evt.setPlacename(objRFields.getString("placename"));
		} catch (Exception ex) {
			evt.setPlacename("");
		}

		try {
			evt.setUid(Integer.parseInt(objRFields.getString("uid"))); //l'id à utiliser dans notre base de données locale
		} catch (Exception ex) {
			// uid = 0
		}

		try {
			evt.setImage(objRFields.getString("image"));// "image":"http://cibul.s3.amazonaws.com/event_quartier-saint-pierre-les-fetes-estivales_366148.jpg",
		} catch (Exception ex) {// absence d'image
			evt.setImage("");
		}

		try {
			evt.setSpaceTimeInfo(objRFields.getString("space_time_info"));// "space_time_info":"du jeudi 27 juillet
																			// au jeudi 17 août à Centre Culturel
																			// Jacques Brel",
		} catch (Exception ex) {
			evt.setSpaceTimeInfo("");
		}

		try {
			evt.setDepartment(objRFields.getString("department"));// "department":"Nord",
		} catch (Exception ex) {
			evt.setDepartment("");
		}

		try {
			evt.setLink(objRFields.getString("link"));// "link":"http://openagenda.com/event/quartier-saint-pierre-les-fetes-estivales",
		} catch (Exception ex) {
			evt.setLink("");
		}

		try {
			evt.setAddress(objRFields.getString("address"));// "address":"rue Jean Baptiste Delescluse Croix",
		} catch (Exception ex) {
			evt.setAddress("");
		}

		try {
			evt.setRegion(objRFields.getString("region"));// "region":"Hauts-de-France",
		} catch (Exception ex) {
			evt.setRegion("");
		}

		try {
			evt.setImageThumb(objRFields.getString("image_thumb"));// "image_thumb":"http://cibul.s3.amazonaws.com/evtbevent_quartier-saint-pierre-les-fetes-estivales_366148.jpg",
		} catch (Exception e) {// absence d'icone
			evt.setImageThumb("");
		}
		
		try {
			evt.setPricingInfo(objRFields.getString("pricing_info"));
		} catch (Exception e) {// absence d'icone
			evt.setPricingInfo("");
		}

		
		try {
			evt.setDescription(objRFields.getString("description"));// "description":"Fêtes estivales"}
		} catch (Exception e) {
			evt.setDescription("");
		}
		// "tags":"saint pierre,croix" - on garde les tags dans un tableau
//		try {
//			String tags = objRFields.getString("tags");
//			if (tags != null && !tags.equals("")) {
//				String[] tagsArray = tags.split(",");
//				for (String sT : tagsArray) {
//					evt.getTags().add(sT);
//				}
//			}
//		} catch (Exception ex) {
//			// tags vides
//		}

		// "updated_at":"2017-09-28T08:49:46+00:00",
		SimpleDateFormat sdfUpdated = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		try {
			evt.setUpdatedAt(sdfUpdated.parse(objRFields.getString("updated_at")));
		} catch (Exception e) {
			evt.setUpdatedAt(null);
		}

		// "timetable":"2017-07-27T14:00:00 2017-07-27T15:00:00;2017-08-17T21:30:00
		// 2017-08-17T22:30:00",
		SimpleDateFormat sdfTimeTable = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat sdfTimeTableNEW = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String timetable = objRFields.getString("timetable");
		if (timetable != null && !timetable.equals("")) {
			String[] timetableArray = timetable.split(";");
			for (String sT : timetableArray) {
				// sT = 2017-07-27T14:00:00 2017-07-27T15:00:00
				String[] oneTTArray = sT.split(" ");
				String startDateTT = "";
				String endDateTT = "";
				try {
					startDateTT = sdfTimeTableNEW.format(sdfTimeTable.parse(oneTTArray[0]));
				} catch (Exception e) {

				}
				try {
					endDateTT = sdfTimeTableNEW.format(sdfTimeTable.parse(oneTTArray[1]));
				} catch (Exception e) {

				}
				// 27/07/2017 14:00:00 - 27/07/2017 15:00:00
//				evt.getTimeTable().add(startDateTT + " - " + endDateTT);
			}
		}
		return evt;
	}
}
