<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="test.dao.UserInfoDAO" >

	<resultMap id="${table.javaObjectCamelName?uncap_first}ResultForList" type="${config.package_prefix}.domain.${table.javaObjectCamelName}">
		<#list table.columnList as col>
		<result column="${col.column_name}" property="${col.column_name?lower_case}" jdbcType="${col.jdbcTypeShortName}" />
		</#list>
	</resultMap>

	<resultMap id="${table.javaObjectCamelName?uncap_first}Result" type="${config.package_prefix}.domain.${table.javaObjectCamelName}" extends="${table.javaObjectCamelName?uncap_first}ResultForList">
	</resultMap>

	<sql id="sf-${table.javaObjectCamelName?uncap_first}">
		<#list table.columnList as col>
			<if test="${col.column_name?lower_case} != null" > and ${col.column_name} = ${"#{"+col.column_name?lower_case+",jdbcType="+col.jdbcTypeShortName+"}"}</if>
		</#list>
	</sql>

	<select id="select${table.javaObjectCamelName}" resultMap="${table.javaObjectCamelName?uncap_first}Result" parameterType="${config.package_prefix}.domain.${table.javaObjectCamelName}">
		select * from ${table.table_name} where 1 = 1
		<include refid="sf-${table.javaObjectCamelName?uncap_first}" />
	</select>

	<select id="select${table.javaObjectCamelName}List" resultMap="${table.javaObjectCamelName?uncap_first}ResultForList" parameterType="${config.package_prefix}.domain.${table.javaObjectCamelName}">
		<if test="row.count != null" >
			<![CDATA[ select * from ( ]]>
		</if>
		select * from ${table.table_name} where 1 = 1
		<include refid="sf-${table.javaObjectCamelName?uncap_first}" />
		<!-- order by ID asc -->
		<if test="row.count != null" >
			<![CDATA[ ) where rownum <= ${r"#{row.count}"}]]>
		</if>
	</select>

	<select id="select${table.javaObjectCamelName}Count" resultType="long" parameterType="${config.package_prefix}.domain.${table.javaObjectCamelName}">
		select count(*) from ${table.table_name} where 1 = 1
		<include refid="sf-${table.javaObjectCamelName?uncap_first}" />
	</select>

	<select id="select${table.javaObjectCamelName}PaginatedList" resultMap="${table.javaObjectCamelName?uncap_first}Result" parameterType="${config.package_prefix}.domain.${table.javaObjectCamelName}">
		<![CDATA[ select * from ( select t_.*, rownum rn_ from ( ]]>
		select * from ${table.table_name} where 1 = 1
		<include refid="sf-${table.javaObjectCamelName?uncap_first}" />
		<!-- order by ID asc -->
		<![CDATA[ ) t_ where rownum <= (${r"#{row.first}"} + ${r"#{row.count}"})) where rn_ >= (${r"#{row.first}"} + 1) ]]>
	</select>

	<insert id="insert${table.javaObjectCamelName}" parameterType="${config.package_prefix}.domain.${table.javaObjectCamelName}">
		<selectKey resultType="long" keyProperty="id">select seq_xxx.nextval as id from dual </selectKey>
		<![CDATA[insert into ${table.table_name}]]>
		 <trim prefix="(" suffix=")" suffixOverrides="," >
			<#list table.columnList as col>
			 <if test="${col.column_name?lower_case} != null" >${col.column_name},</if>
			</#list>
		</trim>
		<trim prefix="values (" suffix=")" suffixOverrides="," >
			<#list table.columnList as col>
			 <if test="${col.column_name?lower_case} != null" >${"#{"+col.column_name+",jdbcType="+col.jdbcTypeShortName+"}"},</if>
			</#list>
		</trim>
	</insert>

	<update id="update${table.javaObjectCamelName}" parameterType="${config.package_prefix}.domain.${table.javaObjectCamelName}">
		update ${table.table_name}
		<trim prefix="SET" suffixOverrides=",">  
			<#list table.columnList as col>
			<if test="${col.column_name?lower_case} != null" >${col.column_name} = ${"#{"+col.column_name+",jdbcType="+col.jdbcTypeShortName+"}"},</if>
			</#list>
		</trim>
		where
		<if test="id != null" >ID = ${r"#{id}"}</if>
		<if test="id == null" >
		  <if test="map.pks != null">
				ID in
				 <foreach collection="map.pks" item="item" index="index" open="(" separator="," close=")">${r"#{item}"}</foreach>
			</if>
		</if>
	</update>

	<delete id="delete${table.javaObjectCamelName}" parameterType="${config.package_prefix}.domain.${table.javaObjectCamelName}">
		delete from ${table.table_name} where
		<if test="id != null" >ID = ${r"#{id}"}</if>
		<if test="id == null" >
		  <if test="map.pks != null">
				ID in
				<foreach collection="map.pks" item="item" index="index" open="(" separator="," close=")">${r"#{item}"}</foreach>
			</if>
		</if>
	</delete>

</mapper>