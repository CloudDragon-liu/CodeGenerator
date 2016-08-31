package com.dragon.codergen.service;

import java.sql.SQLException;
import java.util.List;

import com.dragon.codergen.domain.Table;

/**
 * @author Wu,ShangLong
 * @version builder 2010.02.02
 */
public interface TableService {

	public List<Table> getTableList(String table_names) throws SQLException;

	public List<Table> getAllTableList() throws SQLException;

	public Long getTableCount(String table_names) throws SQLException;

}
