package com.dragon.codergen.generator.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;

import com.dragon.codergen.internal.Constants;

/**
 * @author Wu,ShangLong
 * @version builder 2010.02.04
 */
@Controller
public class IbatisSqlMapConfigXmlGenerator extends AbstractGenerator {

	public void generate() throws SQLException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("tableList", tableFactory.getTableList());
		model.put("config", config);
		model.put("now", new Date());

		String content = super.getTemplateService().getContent("sqlMapConfig.ftl", model);

		// write to file
		StringBuilder nameBuilder = new StringBuilder();
		nameBuilder.append(FILE_IBATIS_SQLMAP_CONFIG).append(Constants.EXTEND_XML);

		StringBuilder pathBuilder = new StringBuilder();
		pathBuilder.append(config.getRealpath()).append(File.separator).append(nameBuilder);

		logger.info(internal.getString("log.generator.run.file", nameBuilder.toString(), pathBuilder.toString()));

		super.writeToFile(pathBuilder.toString(), content);

	}

}
