package attraction.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import attraction.dto.AttractionDto;
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

	@Override
	public List<AttractionDto> searchAttract(int sido, int contentTypeId, String keyword) {
		List<AttractionDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		System.out.println(keyword);
		
		try {
			conn = dbUtil.getConnection();
			if (keyword == null) {
				sql = "select * from attraction_info "
						+ "where content_type_id = ? and sido_code = ?";
			} else {
				sql = "select * from attraction_info "
						+ "where content_type_id = ? and sido_code = ? and title like '%" + keyword + "%'";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, contentTypeId);
			pstmt.setInt(2, sido);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				AttractionDto attract = new AttractionDto();
				attract.setContentId(rs.getInt("content_id"));
				attract.setContentTypeId(rs.getInt("content_type_id"));
				attract.setTitle(rs.getString("title"));
				attract.setAddr1(rs.getString("addr1"));
				attract.setTel(rs.getString("tel"));
				attract.setFirstImage(rs.getString("first_image"));
				attract.setSidoCode(rs.getInt("sido_code"));
				attract.setLatitude(rs.getDouble("latitude"));
				attract.setLongitude(rs.getDouble("longitude"));
				
				list.add(attract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
