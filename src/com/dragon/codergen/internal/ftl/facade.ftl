package ${config.package_prefix}.service;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by liuyunlong
 * @date ${now?string('yyyy-MM-dd HH:mm:ss')}
 */
public interface Facade {

	<#list tableList as table>
	${table.javaObjectCamelName}Service get${table.javaObjectCamelName}Service();
	
	</#list>
}