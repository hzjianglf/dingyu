/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.handany.base.generator;

import freemarker.template.Template;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Administrator
 */
public class Generator {

    private static final String SCHEMA_NAME = "edudb";
    private static final String MODEL_PACKAGE = "com.handany.bm.model";
    private static final String DAO_PACKAGE = "com.handany.bm.dao";
    private static final String SERVICE_PACKAGE = "com.handany.bm.service";
    private static final String SERVICE_IMPL_PACKAGE = "com.handany.bm.service.impl";
    private static final String CONTROLLER_PACKAGE = "com.handany.bm.controller";

    private static final JdbcTemplate jdbcTemplate
            = (JdbcTemplate) new FileSystemXmlApplicationContext("src/main/java/com/handany/base/generator/context.xml").getBean("jdbcTemplate");

    public static List<TableBean> getTables() {
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(new File("src/main/java/com/handany/base/generator/generator.properties")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<TableBean> tableBeanList = jdbcTemplate.query("select * from TABLES where table_schema = ?",
                new Object[]{SCHEMA_NAME},
                new RowMapper<TableBean>() {
                    @Override
                    public TableBean mapRow(ResultSet rs, int i) throws SQLException {
                        TableBean bean = new TableBean();
                        String tableName = rs.getString("table_name");
                        bean.setTableName(tableName);
                        bean.setTableNameNoDash(delDash(tableName));
                        bean.setTableNameCapitalized(StringUtils.capitalize(bean.getTableNameNoDash()));
                        bean.setTableComment(rs.getString("table_comment"));
                        return bean;
                    }
                }
        );

        for (TableBean tableBean : tableBeanList) {
            tableBean.setColumnBeanList(getColumns(tableBean));
        }

        return tableBeanList;
    }

    public static List<ColumnBean> getColumns(final TableBean tableBean) {
        return jdbcTemplate.query("select * from COLUMNS where table_schema = ? and table_name = ?",
                new Object[]{SCHEMA_NAME, tableBean.getTableName()},
                new RowMapper<ColumnBean>() {

                    @Override
                    public ColumnBean mapRow(ResultSet rs, int i) throws SQLException {
                        ColumnBean columnBean = new ColumnBean();
                        String columnName = rs.getString("column_name");
                        columnBean.setColumnName(columnName);
                        columnBean.setColumnNameNoDash(delDash(columnName));
                        columnBean.setColumnNameCapitalized(StringUtils.capitalize(columnBean.getColumnNameNoDash()));
                        columnBean.setColumnComment(rs.getString("column_comment"));

                        String charLength = rs.getString("character_maximum_length");
                        if (StringUtils.isNoneBlank(charLength)) {
                            columnBean.setLength(Long.parseLong(charLength));
                        }

                        String columnType = rs.getString("column_type").toLowerCase();
                        if (columnType.startsWith("varchar") || columnType.startsWith("char")
                        || columnType.startsWith("clob")
                        || ("text").equals(columnType) || ("longtext").equals(columnType)
                        || columnType.startsWith("enum")) {
                            columnBean.setColumnType("String");
                            columnBean.setColumnTypeRsGetter("getString");
                        } else if (columnType.startsWith("tinyint") || columnType.startsWith("smallint")
                        || columnType.startsWith("mediumint")) {
                            columnBean.setColumnType("Integer");
                            columnBean.setColumnTypeRsGetter("getInt");
                        } else if (columnType.startsWith("int") || columnType.startsWith("bigint")) {
                            columnBean.setColumnType("Long");
                            columnBean.setColumnTypeRsGetter("getLong");
                        } else if (("timestamp").equals(columnType) || ("datetime").equals(columnType)
                        || ("date").equals(columnType)) {
                            columnBean.setColumnType("Date");
                            columnBean.setColumnTypeRsGetter("getDate");
                            tableBean.setHasDateColumn(true);
                        } else if (columnType.startsWith("float")) {
                            columnBean.setColumnType("Float");
                            columnBean.setColumnTypeRsGetter("getFloat");
                        } else if (columnType.startsWith("double")) {
                            columnBean.setColumnType("Double");
                            columnBean.setColumnTypeRsGetter("getDouble");
                        } else if (columnType.startsWith("decimal")) {
                            columnBean.setColumnType("BigDecimal");
                            columnBean.setColumnTypeRsGetter("getBigDecimal");
                            tableBean.setHasBigDecimal(true);
                        } else {
                            throw new RuntimeException("Unsupported type: [" + columnType + "]!");
                        }

                        String dataType = rs.getString("data_type").toUpperCase();
                        
                        if ("DATETIME".equals(dataType)) {
                            dataType = "TIMESTAMP";
                        } else if ("TEXT".equals(dataType)) {
                            dataType = "LONGVARCHAR";
                        }
                        
                        columnBean.setColumnJdbcType(dataType);

                        return columnBean;
                    }

                });
    }

    private static String delDash(String str) {
        String lowerCaseStr = str.toLowerCase();
        String[] noDashArray = lowerCaseStr.split("_");
        StringBuilder sb = new StringBuilder(noDashArray[0]);

        for (int i = 1; i < noDashArray.length; i++) {
            sb.append(StringUtils.capitalize(noDashArray[i]));
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        String directoryPath = "src/main/java";

        String modelDirectory = directoryPath + File.separator + StringUtils.replace(MODEL_PACKAGE, ".", File.separator) + File.separator;
        String daoDirectory = directoryPath + File.separator + StringUtils.replace(DAO_PACKAGE, ".", File.separator) + File.separator;
        String serviceDirectory = directoryPath + File.separator + StringUtils.replace(SERVICE_PACKAGE, ".", File.separator) + File.separator;
        String serviceImplDirectory = directoryPath + File.separator + StringUtils.replace(SERVICE_IMPL_PACKAGE, ".", File.separator) + File.separator;
        String controllerDirectory = directoryPath + File.separator + StringUtils.replace(CONTROLLER_PACKAGE, ".", File.separator) + File.separator;
        File fileDirectory = new File(directoryPath);
        if (!fileDirectory.isDirectory()) {
            FileUtils.forceMkdir(fileDirectory);
        }

        List<TableBean> tableBeanList = getTables();

        ArrayList<String> nameList = new ArrayList<>();

//      nameList.add("bm_classroom_course");
        nameList.add("bm_sales_promotion");

        for (TableBean tableBean : tableBeanList) {
            System.out.println("table:" + tableBean.getTableName());
            String tableName = tableBean.getTableName();

            if (!nameList.contains(tableName.toLowerCase())) {
                continue;
            }

            Map<String, Object> varMap = new HashMap<>();
            varMap.put("tableBean", tableBean);
            varMap.put("schemaName", SCHEMA_NAME);
            varMap.put("modelPackage", MODEL_PACKAGE);
            varMap.put("daoPackage", DAO_PACKAGE);
            varMap.put("servicePackage", SERVICE_PACKAGE);
            varMap.put("serviceImplPackage", SERVICE_IMPL_PACKAGE);
            varMap.put("controllerPackage", CONTROLLER_PACKAGE);

            Template modelTemplate = FreemarkerUtil.getTemplate("model.ftl");
            FreemarkerUtil.outputProcessResult(modelDirectory + tableBean.getTableNameCapitalized() + ".java", modelTemplate, varMap);

//            Template daoTemplate = FreemarkerUtil.getTemplate("dao.ftl");
//            FreemarkerUtil.outputProcessResult(daoDirectory + tableBean.getTableNameCapitalized() + "Mapper.java", daoTemplate, varMap);

            Template mapperTemplate = FreemarkerUtil.getTemplate("mapper.ftl");
            FreemarkerUtil.outputProcessResult(daoDirectory + tableBean.getTableNameCapitalized() + "Mapper.xml", mapperTemplate, varMap);

//            Template serviceTemplate = FreemarkerUtil.getTemplate("service.ftl");
//            FreemarkerUtil.outputProcessResult(serviceDirectory + tableBean.getTableNameCapitalized() + "Service.java", serviceTemplate, varMap);
//
//            Template serviceImplTemplate = FreemarkerUtil.getTemplate("serviceimpl.ftl");
//            FreemarkerUtil.outputProcessResult(serviceImplDirectory + tableBean.getTableNameCapitalized() + "ServiceImpl.java", serviceImplTemplate, varMap);
//
//            Template controllerTemplate = FreemarkerUtil.getTemplate("controller.ftl");
//            FreemarkerUtil.outputProcessResult(controllerDirectory + tableBean.getTableNameCapitalized() + "Controller.java", controllerTemplate, varMap);
        }
    }
}
