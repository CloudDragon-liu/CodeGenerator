package com.dragon.codergen.generator.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.dragon.codergen.domain.Table;
import com.dragon.codergen.internal.config.Configuration;
import com.dragon.codergen.service.Facade;
import com.dragon.codergen.utils.UtilsString;

/**
 * @author Wu,ShangLong
 * @version builder 2011.04.22
 */
@Controller("databaseTableFactory")
public class DatabaseTableFactory {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	protected Configuration config;

	@Resource
	private Facade facade;

	public List<Table> tableList;

	@PostConstruct
	protected void init() throws SQLException {
		logger.info("Internal Resource Initialising starting...");
		List<Table> tableList = new ArrayList<Table>();
		if (isGenerateAllTables()) {
			tableList = facade.getTableService().getAllTableList();
		} else {
			tableList = facade.getTableService().getTableList(config.getTable_names());
		}
		this.tableList = tableList;
		logger.info("Internal Resource Initialising ended.");
	}

	/**
	 * 配置文件表名为空或者为all，则为取所有表名称
	 * liuyunlong at 2016年8月31日
	 * @return
	 */
	protected Boolean isGenerateAllTables() {
		String tablenames = UtilsString.removeQuote(config.getTable_names()).trim();
		return StringUtils.isBlank(tablenames) || StringUtils.equalsIgnoreCase(tablenames, "all");
	}

	public List<Table> getTableList() {
		return tableList;
	}

}
