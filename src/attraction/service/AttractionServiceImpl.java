package attraction.service;

import java.util.List;

import attraction.dao.AttractionDao;
import attraction.dao.AttractionDaoImpl;
import attraction.dto.AttractionDto;
import attraction.dto.SidoDto;

public class AttractionServiceImpl implements AttractionService {
	
	private static AttractionService attrService = new AttractionServiceImpl();
	private AttractionDao attrDao;
	
	private AttractionServiceImpl() {
		attrDao = AttractionDaoImpl.getInstance();
	}
	
	public static AttractionService getInstance() {
		return attrService;
	}

	@Override
	public List<SidoDto> getAreaList() {
		return attrDao.getAreaList();
	}

	@Override
	public List<AttractionDto> searchAttract(int sido, int contentId) {
		return attrDao.searchAttract(sido, contentId);
	}

}
 