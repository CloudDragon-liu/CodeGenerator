package com.dragon.codergen.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dragon.codergen.service.Facade;
import com.dragon.codergen.service.TableService;

/**
 * @author Wu,ShangLong
 * @version builder 2010.02.02
 */
@Service("facade")
public class FacadeImpl implements Facade {

	@Resource
	TableService tableService;

	public TableService getTableService() {
		return tableService;
	}

}
