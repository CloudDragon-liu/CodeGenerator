package com.dragon.codergen.dao.sqlserver;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.dragon.codergen.dao.ColumnDao;
import com.dragon.codergen.domain.Column;
import com.dragon.codergen.utils.JavaTypeNameTranslator;
import com.dragon.codergen.utils.JdbcTypeNameTranslator;
import com.dragon.codergen.utils.UtilsString;

/**
 * @author Wu,ShangLong
 * @version builder 2011.04.22
 */
public class ColumnDaoSqlServerImpl extends BaseDaoSqlServerImpl implements ColumnDao {
	public List<Column> selectColumnList(String tableName) throws SQLException {
		if (StringUtils.isBlank(tableName)) {
			throw new NullPointerException("Table name for searching column must not be empty, please check yourself!");
		}

		List<Column> columnList = new ArrayList<Column>();
		DatabaseMetaData metaData = super.getConnection(false).getMetaData();
		ResultSet rs = metaData.getColumns(null, null, tableName, null);
		while (rs.next()) {
			Column column = new Column();
			column.setTable_name(rs.getString("TABLE_NAME"));
			column.setColumn_name(rs.getString("COLUMN_NAME"));
			column.setData_type(rs.getInt("DATA_TYPE"));
			column.setType_name(rs.getString("TYPE_NAME"));
			column.setColumn_size(rs.getInt("COLUMN_SIZE"));
			column.setDecimal_digits(rs.getInt("DECIMAL_DIGITS"));
			column.setNum_prec_radix(rs.getInt("NUM_PREC_RADIX"));
			column.setRemarks(rs.getString("REMARKS"));
			column.setColumn_def(rs.getString("COLUMN_DEF"));
			column.setIs_nullable(rs.getString("IS_NULLABLE"));

			column.setJavaObjectCamelName(UtilsString.camelize(column.getColumn_name()));

			column.setJavaTypeFullName(JavaTypeNameTranslator.getJavaTypeName(column.getData_type(), column
					.getColumn_size(), column.getDecimal_digits()));
			column.setJavaTypeShortName(StringUtils.substringAfterLast(column.getJavaTypeFullName(), "."));
			column.setJdbcTypeFullName(JdbcTypeNameTranslator.getJdbcTypeName(column.getData_type()));
			column.setJdbcTypeShortName(JdbcTypeNameTranslator.getJdbcTypeName(column.getData_type()));

			columnList.add(column);

			logger.debug(column.toString());
		}
		rs.close();
		return columnList;
	}
}
