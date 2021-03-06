package com.dragon.codergen.dao.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author Wu,ShangLong
 * @version builder 2011.04.22
 */
public class BaseDaoSqlServerImpl {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected Connection conn = null;

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

	protected Connection getConnection(boolean createNew) {
		if (createNew) {
			try {
				if (null != conn) {
					conn.close();
				}
				conn = dataSource.getConnection();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
		return conn;
	}

	protected Connection getConnection() {
		return getConnection(true);
	}

	protected Connection closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return conn;
	}
}
