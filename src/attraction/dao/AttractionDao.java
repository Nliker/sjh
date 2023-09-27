package attraction.dao;
import attraction.dto.*;

import java.util.*;

public interface AttractionDao {
	List<SidoDto> getAreaList();
	List<AttractionDto> searchAttract(int sido, int contentId, String keyword);
}
