<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${daoPackage}.${tableBean.tableNameCapitalized}Mapper">
    <resultMap id="BaseResultMap" type="${modelPackage}.${tableBean.tableNameCapitalized}">
        <#list tableBean.columnBeanList as columnBean>
            <result column="${columnBean.columnName}" jdbcType="${columnBean.columnJdbcType}" property="${columnBean.columnNameNoDash}" />
        </#list>
    </resultMap>

    <sql id="BaseColumnList">
        <#list tableBean.columnBeanList as columnBean>
            ${columnBean.columnName}<#if columnBean_has_next>,</#if>
        </#list>
    </sql>
</mapper>