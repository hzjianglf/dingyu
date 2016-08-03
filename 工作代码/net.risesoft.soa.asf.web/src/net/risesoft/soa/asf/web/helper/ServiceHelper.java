package net.risesoft.soa.asf.web.helper;

import egov.appservice.asf.annotation.Error;
import egov.appservice.asf.annotation.MetaInfo;
import egov.appservice.asf.annotation.Parameter;
import egov.appservice.asf.annotation.Result;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.risesoft.soa.asf.core.ServiceRepository;
import net.risesoft.soa.asf.core.ServiceRepositoryHolder;
import net.risesoft.soa.asf.core.thirdparty.ThirdpartyServiceRepository;
import net.risesoft.soa.asf.model.RepositoryInfo;
import net.risesoft.soa.asf.util.wsdlparser.WebMethod;
import net.risesoft.soa.asf.util.wsdlparser.WebObject;
import net.risesoft.soa.asf.util.wsdlparser.WebParam;
import net.risesoft.soa.asf.util.wsdlparser.WsdlParser;
import org.springframework.stereotype.Component;

@Component
public class ServiceHelper
{
  public RepoState getRepoState(String name)
  {
    ServiceRepository sr = ServiceRepositoryHolder.getRepository(name);
    RepoState state = new RepoState(true, "状态: 正常");
    if ((sr != null) && (sr.isRemote())) {
      String host = sr.getMetaData().getIp();
      String port = sr.getMetaData().getPort();
      String basePath = sr.getMetaData().getBasePath();
      try {
        URL url = new URL("http://" + host + ":" + port + "/services" + basePath);
        HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
        int respCode = httpConn.getResponseCode();
        state.setState(respCode == 200);
        if (!(state.getState())) state.setStateMsg("HTTP " + respCode + " : " + httpConn.getResponseMessage());
      } catch (Throwable ex) {
        state.setState(false);
        state.setStateMsg(ex.toString());
      }
    }
    return state;
  }

  public boolean getCompState(String repoId, String compId) {
    ServiceRepository sr = ServiceRepositoryHolder.getRepository(repoId);
//    sr instanceof ThirdpartyServiceRepository;
    return true;
  }

  public List<Map<String, Object>> fetchOperatesFromWSDL(String wsdlLocation) {
    List model = new ArrayList();
    WsdlParser parser = new WsdlParser(wsdlLocation);
    try
    {
      WebObject webObj = parser.parse();

      for (WebMethod method : webObj.getMethods()) {
        Map map = new HashMap();
        map.put("name", method.getName());
        map.put("chsname", method.getDocumentation());

        List <WebParam>inputParamList = method.getInputParams();
        StringBuilder paramsStr = new StringBuilder();
        for (WebParam param : inputParamList) {
          paramsStr.append(param.getType()).append("&nbsp;&nbsp;").append(param.getName()).append("<BR />");
        }
        map.put("params", paramsStr.toString());
        WebParam outputParam = method.getOutputParam();
        paramsStr = new StringBuilder();
        if (outputParam != null) {
          String type = translateParamType(outputParam.getType());
          paramsStr.append(type).append("<BR />");
        }
        map.put("returnType", (paramsStr.toString().length() > 0) ? paramsStr.toString() : "无返回值");

        model.add(map);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return model;
  }

  private String translateParamType(String oParam) {
    if ((oParam != null) && (oParam.startsWith("ArrayOf"))) {
      return "List&lt;" + 
        oParam.substring("ArrayOf".length()) + 
        "&gt;";
    }

    return oParam;
  }

  public List<Map<String, Object>> fetchOperatesFromClass(Class cls) {
    Method[] methods = (cls.isInterface()) ? cls.getMethods() : cls.getDeclaredMethods();
    List model = new ArrayList(methods.length);
    for (Method m : methods) {
      if ((m.getModifiers() & 0x1) != 0) {
        Map map = new HashMap();
        map.put("name", m.getName());
        StringBuffer paramsStr = new StringBuffer();
        MetaInfo meta = (MetaInfo)m.getAnnotation(MetaInfo.class);
        Parameter[] params = (meta != null) ? meta.params() : null;
        int index = 0;
        for (Class p : m.getParameterTypes()) {
          paramsStr.append(p.getCanonicalName())
            .append(((params != null) && (params.length > index)) ? "&nbsp;&nbsp;" + params[index].name() : "")
            .append("<BR />");
          ++index;
        }
        map.put("chsname", (meta != null) ? meta.name() : "");
        map.put("params", paramsStr.toString());
        map.put("returnType", m.getReturnType().getCanonicalName());
        detectMetaInfo(m, map);
        model.add(map);
      }
    }
    return model;
  }

  private void detectMetaInfo(Method m, Map map) {
    MetaInfo meta = (MetaInfo)m.getAnnotation(MetaInfo.class);
    if (meta != null) {
      map.put("methodDesc", meta.desc());
      StringBuffer pNames = new StringBuffer();
      Parameter[] params = meta.params();
      for (Parameter p : params) {
        pNames.append("<p  style='text-indent: 80px'>")
          .append(p.name())
          .append(":&nbsp;&nbsp;")
          .append(p.desc())
          .append("</p>");
      }
      map.put("paramNames", pNames.toString());
      StringBuffer errorsStr = new StringBuffer();
      Error[] errors = meta.errors();
      for (Error e : errors) {
        errorsStr.append("<p  style='text-indent: 80px'>")
          .append(e.type().getCanonicalName())
          .append(":&nbsp;&nbsp;")
          .append(e.desc())
          .append("</p>");
      }
      map.put("errors", errorsStr.toString());
      map.put("result", meta.result().value());
    } else {
      map.put("methodDesc", "");
      map.put("paramNames", "");
      map.put("errors", "");
      map.put("result", "");
    }
  }

  public static class RepoState
  {
    private boolean state;
    private String msg;

    public RepoState(boolean state, String msg)
    {
      this.state = state;
      this.msg = msg;
    }

    public boolean getState() {
      return this.state;
    }

    public void setState(boolean ok) {
      this.state = ok;
    }

    public String getStateMsg() {
      return this.msg;
    }

    public void setStateMsg(String msg) {
      this.msg = msg;
    }
  }
}