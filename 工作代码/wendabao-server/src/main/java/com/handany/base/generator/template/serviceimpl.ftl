package ${serviceImplPackage};

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handany.base.common.CommonUtils;
import com.handany.base.sequence.SerialNumberManager;
import ${daoPackage}.${tableBean.tableNameCapitalized}Mapper;
import ${modelPackage}.${tableBean.tableNameCapitalized};
import ${servicePackage}.${tableBean.tableNameCapitalized}Service;

@Service
public class ${tableBean.tableNameCapitalized}ServiceImpl implements ${tableBean.tableNameCapitalized}Service {
    @Autowired
    private ${tableBean.tableNameCapitalized}Mapper ${tableBean.tableNameNoDash}Mapper;

    @Autowired
    private SerialNumberManager serialNumberManager;

}
