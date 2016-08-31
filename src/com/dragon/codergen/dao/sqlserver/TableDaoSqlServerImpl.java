package com.dragon.codergen.dao.sqlserver;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.dragon.codergen.dao.TableDao;
import com.dragon.codergen.domain.Table;
import com.dragon.codergen.utils.UtilsString;

/**
 * @author Wu,ShangLong
 * @version builder 2011.04.22
 */
public class TableDaoSqlServerImpl extends BaseDaoSqlServerImpl implements TableDao {

	public Long selectAllTableCount() throws SQLException {
		Long count = new Long("0");
		DatabaseMetaData metaData = super.getConnection(false).getMetaData();
		ResultSet rs = metaData.getTables(null, dataSource.getUsername().toUpperCase(), null, new String[] { "TABLE" });

		while (rs.next()) {

			if (rs.getString("TABLE_NAME").contains("$") || StringUtils.isBlank(rs.getString("TABLE_NAME"))) {
				continue;
			}
			count++;
		}
		return count;
	}

	public List<Table> selectAllTableList() throws SQLException {
		List<Table> tableList = new ArrayList<Table>();

		DatabaseMetaData metaData = super.getConnection(false).getMetaData();
		ResultSet rs = metaData.getTables(null, null, null, new String[] { "TABLE" });

		while (rs.next()) {

			if (rs.getString("TABLE_NAME").contains("$") || StringUtils.isBlank(rs.getString("TABLE_NAME"))) {
				continue;
			}

			Table table = new Table();
			table.setType_cat(rs.getString("TABLE_CAT"));
			table.setType_schem(rs.getString("TABLE_SCHEM"));
			table.setTable_name(rs.getString("TABLE_NAME"));
			table.setTable_type(rs.getString("TABLE_TYPE"));
			table.setRemarks(rs.getString("REMARKS"));
			table.setJavaObjectCamelName(UtilsString.camelize(table.getTable_name()));
			tableList.add(table);

			logger.debug(table.toString());
		}
		rs.close();

		return tableList;
	}

	public Long selectTableCount(String tableNames) throws SQLException {
		String[] tableNameArray = tableNames.split(",");

		Long count = new Long("0");
		for (String s : tableNameArray) {
			if (s.contains("$") || StringUtils.isBlank(s)) {
				continue;
			}
			count++;
		}
		return count;
	}

	public List<Table> selectTableList(String tableNames) throws SQLException {
		List<Table> tableList = new ArrayList<Table>();

		String[] tableNameArray = tableNames.split(",");

		for (String s : tableNameArray) {
			if (s.contains("$") || StringUtils.isBlank(s)) {
				continue;
			}

			s = UtilsString.removeQuote(s).trim().toUpperCase();

			Table t = new Table();
			t.setTable_name(s);
			t.setJavaObjectCamelName(UtilsString.camelize(t.getTable_name()));
			tableList.add(t);
		}

		return tableList;
	}

}
