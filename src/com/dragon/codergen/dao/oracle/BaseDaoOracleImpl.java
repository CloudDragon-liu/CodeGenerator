package com.dragon.codergen.dao.oracle;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import oracle.jdbc.OracleConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author Wu,ShangLong
 * @version builder 2010.02.02
 */
public class BaseDaoOracleImpl {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected OracleConnection conn = null;

	@Resource
	protected DriverManagerDataSource dataSource;

	@PostConstruct
	public void init() {
		getConnection();
	}

	@PreDestroy
	public void destroy() {
		closeConnection();
	}

	protected OracleConnection getConnection(boolean createNew) {
		if (createNew) {
			try {
				if (null != conn) {
					conn.close();
				}
				conn = (oracle.jdbc.OracleConnection) dataSource.getConnection();
				conn.setRemarksReporting(true);
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
		return conn;
	}

	protected OracleConnection getConnection() {
		return getConnection(true);
	}

	protected OracleConnection closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return conn;
	}
}
