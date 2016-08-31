package com.dragon.codergen.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dragon.codergen.dao.ColumnDao;
import com.dragon.codergen.dao.TableDao;
import com.dragon.codergen.domain.Table;
import com.dragon.codergen.service.TableService;

/**
 * @author Wu,ShangLong
 * @version builder 2010.02.02
 */
@Service
public class TableServiceImpl extends BaseServiceImpl implements TableService {

	@Resource
	ColumnDao columnDao;

	@Resource
	TableDao tableDao;

	public List<Table> getAllTableList() throws SQLException {

		List<Table> tableList = tableDao.selectAllTableList();

		for (int i = 0; i < tableList.size(); i++) {
			String name = tableList.get(i).getTable_name();
			logger.info("Searching table[mode = all], {}.", name);
			tableList.get(i).setColumnList(columnDao.selectColumnList(name));
		}

		logger.info("Table List Size : {}", tableList.size());
		return tableList;
	}

	public List<Table> getTableList(String table_names) throws SQLException {
		List<Table> tableList = tableDao.selectTableList(table_names);

		for (int i = 0; i < tableList.size(); i++) {
			String name = tableList.get(i).getTable_name();
			logger.info("Searching table[mode = given], {}.", name);
			tableList.get(i).setColumnList(columnDao.selectColumnList(name));
		}

		logger.info("Table List Size : {}", tableList.size());
		return tableList;
	}

	public Long getTableCount(String tableNames) throws SQLException {
		return tableDao.selectTableCount(tableNames);
	}

}
