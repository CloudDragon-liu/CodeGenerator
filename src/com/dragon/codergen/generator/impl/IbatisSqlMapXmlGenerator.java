package com.dragon.codergen.generator.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;

import com.dragon.codergen.domain.Table;
import com.dragon.codergen.internal.Constants;

/**
 * @author Wu,ShangLong
 * @version builder 2010.02.04
 */
@Controller
public class IbatisSqlMapXmlGenerator extends AbstractGenerator {

	public void generate() throws SQLException {
		for (Table t : tableFactory.getTableList()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("table", t);
			model.put("config", config);
			model.put("now", new Date());

			String content = super.getTemplateService().getContent("daoImplSqlMap.ftl", model);

			// write to file
			StringBuilder nameBuilder = new StringBuilder();
			nameBuilder.append(t.getTable_name()).append(FILE_SUFFIX_IBATIS_SQLMAP_XML).append(Constants.EXTEND_XML);

			StringBuilder pathBuilder = new StringBuilder();
			pathBuilder.append(config.getRealpath_dao_impl_maps()).append(File.separator).append(nameBuilder);

			logger.info(internal.getString("log.generator.run.file", nameBuilder.toString(), pathBuilder.toString()));

			super.writeToFile(pathBuilder.toString(), content);

		}
	}

}
