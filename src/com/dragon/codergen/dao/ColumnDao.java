package com.dragon.codergen.dao;

import java.sql.SQLException;
import java.util.List;

import com.dragon.codergen.domain.Column;

/**
 * @author Wu,ShangLong
 * @version builder 2010.02.02
 */
public interface ColumnDao {

	public List<Column> selectColumnList(String tableName) throws SQLException;

}
