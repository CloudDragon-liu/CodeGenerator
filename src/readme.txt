
【PROJECT HOME】
	http://code.google.com/p/codergenerator/

【SVN CHECKOUT】
	http://codergenerator.googlecode.com/svn/trunk/ [read-only]

【注意】
	1. 生成器jre运行环境为 1.6.0或以上；
	2. 生成代码运行环境需要的jar：ebiz-ssi-1.1.jar,ibatis-2.3.4.726.jar,spring-2.5.6.jar，jdk1.5.0或以上
	3. 项目文件编码采用UTF-8
	
【版本变更】
	v1.0. coder_generator_v_1.0（2010.02.05）, 处女作
		1) 数据库支持oracle
		2) 可生成dao、daoImpl、service、serviceImpl、facade、facadeImpl、ibatisXml、ibatisConfigXml

	v1.1. coder_generator_v_1.1（2010.02.09）
		1) 支持oracle、mysql、sqlserver数据库
		2) 提升了代码执行速度
		
	v1.2. coder_generator_v_1.2（2010.02.20）
		1) 修改了com\xingxd\codergen\internal\ftl\daoImplSqlMap.ftl，改正了sql语句拼写错误的问题
		2) 添加了config.properties中部分注释
		
	v1.21. coder_generator_v_1.21（2010.04.02）
		1) 修改了daoImplSqlMap.ftl中查询count时的语句, 从“select * from xxx”改为“select count(*) from xxx”;
		2) 修改了daoImplSqlMap.ftl中查询PaginatedList的Select标签,Line52 追加 "  ]]>".