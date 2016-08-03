package ${modelPackage};

<#if (tableBean.hasDateColumn)>
import java.util.Date;
</#if>
<#if (tableBean.hasBigDecimal)>
import java.math.BigDecimal;
</#if>

public class ${tableBean.tableNameCapitalized} {
<#list tableBean.columnBeanList as columnBean>
    <#if ('' != columnBean.columnComment)>
    /**
     * ${columnBean.columnComment}
     **/
    </#if>
    private ${columnBean.columnType} ${columnBean.columnNameNoDash};

</#list>

<#list tableBean.columnBeanList as columnBean>
    public void set${columnBean.columnNameCapitalized}(${columnBean.columnType} ${columnBean.columnNameNoDash}) {
        this.${columnBean.columnNameNoDash} = ${columnBean.columnNameNoDash};
    }

    public ${columnBean.columnType} get${columnBean.columnNameCapitalized}() {
        return ${columnBean.columnNameNoDash};
    }

</#list>
}
