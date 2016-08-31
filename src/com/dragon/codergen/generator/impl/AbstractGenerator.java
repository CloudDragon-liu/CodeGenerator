package com.dragon.codergen.generator.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dragon.codergen.domain.Table;
import com.dragon.codergen.internal.Constants;
import com.dragon.codergen.internal.config.Configuration;
import com.dragon.codergen.service.TemplateService;
import com.dragon.codergen.utils.Messages;
import com.dragon.codergen.utils.UtilsString;

/**
 * @author Wu,ShangLong
 * @version builder 2010.02.03
 */
public abstract class AbstractGenerator {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	protected Configuration config;

	@Resource
	protected Messages internal;

	@Resource
	protected DatabaseTableFactory tableFactory;

	@Resource
	private TemplateService templateService;

	protected static final String FILE_FACADE = "Facade";

	protected static final String FILE_FACADE_IMPL = "FacadeImpl";

	protected static final String FILE_SUFFIX_DAO = "Dao";

	protected static final String FILE_SUFFIX_DAO_IMPL = "DaoSqlMapImpl";

	protected static final String FILE_SUFFIX_IBATIS_SQLMAP_XML = "_SqlMap";

	protected static final String FILE_SUFFIX_SERVICE = "Service";

	protected static final String FILE_SUFFIX_SERVICE_IMPL = "ServiceImpl";

	protected static final String FILE_IBATIS_SQLMAP_CONFIG = "sqlmap-config";

	public static List<Table> tableList;

	protected TemplateService getTemplateService() {
		return templateService;
	}

	protected Boolean isGenerateAllTables() {
		String tablenames = UtilsString.removeQuote(config.getTable_names()).trim();
		return StringUtils.isBlank(tablenames) || StringUtils.equalsIgnoreCase(tablenames, "all");
	}

	protected Boolean isZipFile() {
		return new Boolean(UtilsString.removeQuote(config.getWorkspace_zip()).trim());
	}

	protected void writeToFile(String realPath, String content) {
		try {
			FileUtils.writeStringToFile(new File(realPath), content, Constants.FILE_ENCODING);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

}
