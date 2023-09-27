package attraction.service;

import java.util.List;

import attraction.dto.AttractionDto;
import attraction.dto.SidoDto;

public interface AttractionService {
	List<SidoDto> getAreaList();
	List<AttractionDto> searchAttract(int sidoCode, int contentTypeId, String keyword);
}
