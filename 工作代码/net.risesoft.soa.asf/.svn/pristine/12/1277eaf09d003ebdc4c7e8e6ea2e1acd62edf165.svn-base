package net.risesoft.soa.asf.core.parser;

import egov.appservice.asf.model.InvokeMode;
import egov.appservice.asf.model.InvokeStyle;
import egov.appservice.asf.model.Transport;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import net.risesoft.soa.asf.model.InvokeInfo;
import net.risesoft.soa.asf.model.ModuleProvider;
import net.risesoft.soa.asf.model.ModuleVender;
import net.risesoft.soa.asf.model.ServiceModule;
import net.risesoft.soa.asf.util.XMLHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ASFDefinitionParser
{
  private static final Logger log = LoggerFactory.getLogger(ASFDefinitionParser.class);

  public static ServiceModule load(InputStream is) throws SAXException, IOException {
    return load(is, true);
  }

  public static ServiceModule load(InputStream is, boolean validate) throws SAXException, IOException {
    return load(is, null, validate);
  }

  public static ServiceModule load(URL url, boolean validate) throws SAXException, IOException {
    return load(url.openStream(), url, validate);
  }

  private static ServiceModule load(InputStream is, URL url, boolean validate) throws SAXException, IOException {
    log.debug("starting parse asf definition file.......");
    long begin = System.currentTimeMillis();

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    if (validate) dbf.setSchema(getSchema());
    DocumentBuilder db;
    try {
      db = dbf.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      throw new SAXException("Error creating document builder", e);
    }
    db.setErrorHandler(new ErrorHandler()
    {
      private String processMsg(SAXParseException e) {
        String msg = e.getLocalizedMessage();
        int line = e.getLineNumber();
        int col = e.getColumnNumber();
        return msg + "(at line: " + line + ", col: " + col + ")";
      }

      public void error(SAXParseException e) throws SAXException {
        String msg = processMsg(e);
        if (msg.indexOf("xsi:noNamespaceSchemaLocation") > 0) {
          ASFDefinitionParser.log.debug(msg);
        } else {
          ASFDefinitionParser.log.error(msg);
          throw e;
        }
      }

      public void fatalError(SAXParseException e) throws SAXException {
        ASFDefinitionParser.log.error(processMsg(e));
        throw e;
      }

      public void warning(SAXParseException e) throws SAXException {
        ASFDefinitionParser.log.warn(processMsg(e));
      }
    });
    Document doc = db.parse(is);

    Element root = (Element)doc.getElementsByTagName("ServiceModule").item(0);
    ServiceModule module = buildModuleInfo(root);
    module.setComponents(buildComponentList(root));

    long end = System.currentTimeMillis();
    log.debug("started parse during: " + (end - begin) + " ms");
    return module;
  }

  private static Schema getSchema() throws SAXException {
    SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
    InputStream isSchema = ASFDefinitionParser.class.getResourceAsStream("/net/risesoft/soa/asf/core/parser/ServiceModule_1.1.xsd");
    StreamSource ss = new StreamSource(isSchema);
    return factory.newSchema(ss);
  }

  private static ServiceModule buildModuleInfo(Element root) {
    ServiceModule module = new ServiceModule();
    module.setId(root.getAttribute("id"));
    module.setName(root.getAttribute("name"));
    module.setVersion(root.getAttribute("version"));
    module.setDescription(XMLHelper.getChildText(root, "description"));

    List list = XMLHelper.getChildElementsByPath(root, "info/provider/business");
    List bizList = new ArrayList(list.size());
    Element b;
    for (Iterator localIterator1 = list.iterator(); localIterator1.hasNext(); ) { b = (Element)localIterator1.next();
      bizList.add(buildModuleProvider(b));
    }
    module.setProviders(bizList);

    list = XMLHelper.getChildElementsByPath(root, "info/vender/business");
    bizList = new ArrayList(list.size());
    for (localIterator1 = list.iterator(); localIterator1.hasNext(); ) { b = (Element)localIterator1.next();
      bizList.add(buildModuleVender(b));
    }
    module.setVenders(bizList);

    list = XMLHelper.getChildElementsByPath(root, "info/dependence/module");
    Map dMap = new HashMap(list.size());
    for (Element d : list) {
      String id = d.getAttribute("id");
      String version = d.getAttribute("version");
      dMap.put(id, version);
    }
    module.setDependences(dMap);
    return module;
  }

  private static List<egov.appservice.asf.model.ServiceComponent> buildComponentList(Element root) {
    List eList = XMLHelper.getChildElements(root, "ServiceComponent");
    List compList = new ArrayList(eList.size());
    for (Element c : eList) {
      net.risesoft.soa.asf.model.ServiceComponent comp = new net.risesoft.soa.asf.model.ServiceComponent();
      comp.setId(c.getAttribute("id"));
      comp.setName(c.getAttribute("name"));
      comp.setVersion(c.getAttribute("version"));
      comp.setDescription(XMLHelper.getChildText(c, "description"));
      comp.setCategory(XMLHelper.getChildText(c, "category"));
      comp.setInterfaze(XMLHelper.getAttribute(XMLHelper.getChildElement(c, "service"), "interface"));
      comp.setFactoryMethod(XMLHelper.getAttribute(XMLHelper.getChildElement(c, "implementation"), "factory-method"));
      comp.setImplementation(XMLHelper.getAttribute(XMLHelper.getChildElement(c, "implementation"), "class"));
      comp.setWsdl(XMLHelper.getChildText(c, "wsdl"));
      comp.setInvokeInfo(buildInvokeInfo(XMLHelper.getChildElement(c, "invoke")));
      comp.setSignature(XMLHelper.getChildText(c, "signature"));
      comp.setProperties(buildProperties(XMLHelper.getChildElement(c, "properties")));
      compList.add(comp);
    }
    return compList;
  }

  private static ModuleProvider buildModuleProvider(Element e) {
    ModuleProvider be = new ModuleProvider();
    if (e == null) return be;

    be.setName(XMLHelper.getChildText(e, "name"));
    be.setWebsite(XMLHelper.getChildText(e, "website"));
    be.setAddress(XMLHelper.getChildText(e, "address"));
    be.setContact(XMLHelper.getChildText(e, "contact"));
    be.setTelephone(XMLHelper.getChildText(e, "tel"));
    be.setEmail(XMLHelper.getChildText(e, "email"));
    return be;
  }

  private static ModuleVender buildModuleVender(Element e) {
    ModuleVender be = new ModuleVender();
    if (e == null) return be;

    be.setName(XMLHelper.getChildText(e, "name"));
    be.setWebsite(XMLHelper.getChildText(e, "website"));
    be.setAddress(XMLHelper.getChildText(e, "address"));
    be.setContact(XMLHelper.getChildText(e, "contact"));
    be.setTelephone(XMLHelper.getChildText(e, "tel"));
    be.setEmail(XMLHelper.getChildText(e, "email"));
    return be;
  }

  private static InvokeInfo buildInvokeInfo(Element e) {
    InvokeInfo ii = new InvokeInfo();
    if (e == null) return ii;
    ii.setMode(InvokeMode.valueOf(XMLHelper.getChildText(e, "mode")));
    if (ii.getMode() == null) ii.setMode(InvokeMode.sync);
    ii.setStyle(InvokeStyle.valueOf(XMLHelper.getChildText(e, "style")));
    if (ii.getStyle() == null) ii.setStyle(InvokeStyle.rpc);
    ii.setTransport(Transport.valueOf(XMLHelper.getChildText(e, "transport")));
    if (ii.getTransport() == null) ii.setTransport(Transport.HTTP);
    return ii;
  }

  private static Map<String, String> buildProperties(Element e) {
    Map map = new HashMap();
    if (e == null) return map;
    List list = XMLHelper.getChildElements(e, "property");
    for (Element p : list) {
      map.put(p.getAttribute("key"), p.getAttribute("value"));
    }
    return map;
  }
}