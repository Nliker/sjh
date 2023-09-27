package util;

import java.util.List;

import attraction.dto.AttractionDto;

public class ToJSON {

	public static String attractionDtoListToJSON(List<AttractionDto> list) {
		StringBuilder json = new StringBuilder();
		json.append("{ \"data\" : [ ");
		
		for(int i = 0; i < list.size(); i++) {
			AttractionDto attr = list.get(i);
			json.append("{ ");
			json.append("\"contentid\" : \"").append(attr.getContentId()).append("\", ");
			json.append("\"contenttypeid\" : \"").append(attr.getContentTypeId()).append("\", ");
			json.append("\"title\" : \"").append(attr.getTitle()).append("\", ");
			json.append("\"addr1\" : \"").append(attr.getAddr1()).append("\", ");
			json.append("\"tel\" : \"").append(attr.getTel().replaceAll("\\\\", "").replaceAll("\"", "")).append("\", ");
			json.append("\"firstimage\" : \"").append(attr.getFirstImage()).append("\", ");
			json.append("\"lat\" : \"").append(attr.getLatitude()).append("\", ");
			json.append("\"lng\" : \"").append(attr.getLongitude());
			json.append("\" }");
			if(i < list.size() - 1) json.append(" ,");
		}
		json.append(" ] }");
		return json.toString();
	}
}
