package attraction.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import attraction.dto.SidoDto;
import dbUtil.DBUtil;

public class AttractionDaoImpl implements AttractionDao {
	
	private static AttractionDao attrDao = new AttractionDaoImpl();
	private static DBUtil dbUtil;
	
	private AttractionDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}
	
	public static AttractionDao getInstance() {
		return attrDao;
	}

	@Override
	public List<SidoDto> getAreaList() {
		List<SidoDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder("select sido_code, sido_name from sido");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SidoDto sido = new SidoDto();
				sido.setSidoCode(rs.getInt("sido_code"));
				sido.setSidoName(rs.getString("sido_name"));
				list.add(sido);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
