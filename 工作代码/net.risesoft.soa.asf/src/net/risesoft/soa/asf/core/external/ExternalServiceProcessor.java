package net.risesoft.soa.asf.core.external;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import net.risesoft.soa.asf.core.ServiceMetaData;
import net.risesoft.soa.asf.core.ServiceProcessor;
import net.risesoft.soa.asf.core.ServiceRepository;
import net.risesoft.soa.asf.core.ServiceRepositoryHolder;
import net.risesoft.soa.asf.core.parser.ASFDefinitionParser;
import net.risesoft.soa.asf.model.ServiceModule;
import net.risesoft.soa.asf.util.dirwatcher.DirectoryWatcher;
import net.risesoft.soa.asf.util.dirwatcher.FileListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExternalServiceProcessor
  implements ServiceProcessor
{
  private static final Logger log = LoggerFactory.getLogger(ExternalServiceProcessor.class);

  private static final String fileSep = System.getProperty("file.separator");

  private static final String storePath = System.getProperty("user.home") + fileSep + ".risenet" + fileSep + 
    "asf-external-services";
  private DirectoryWatcher dirWatcher;
  private FileListener fileListener = new FileListener()
  {
    public void onAdd(Object newResource) {
      if (newResource instanceof File) {
        File file = (File)newResource;
        if ((file.isFile()) && (file.getName().endsWith(".xml")))
          ExternalServiceProcessor.this.processDefinitionFile(file);
      }
    }
  };
  private ServiceRepository serviceRepository;

  public void destroy()
  {
    this.dirWatcher.stop();
    this.dirWatcher.removeAllListeners();
    this.dirWatcher = null;
    ServiceRepository sr = ServiceRepositoryHolder.removeRepository("asf-external-repository");
    if (sr == null) return; sr.clear();
  }

  public void refresh() {
    if (ServiceRepositoryHolder.getRepository("asf-external-repository") == null) {
      ServiceRepositoryHolder.putRepository(new ExternalServiceRepository());
    }
    this.serviceRepository = ServiceRepositoryHolder.getRepository("asf-external-repository");
    this.serviceRepository.clear();
    File[] files = getDefinitionFiles();
    for (File f : files) {
      processDefinitionFile(f);
    }
    this.dirWatcher = new DirectoryWatcher(storePath, 10);
    this.dirWatcher.addListener(this.fileListener);
    this.dirWatcher.start();
  }

  private void processDefinitionFile(File f) {
    log.debug("found asf definition file[" + f.getAbsolutePath() + "]");
    ServiceModule module = null;
    InputStream is = null;
    try {
      is = new FileInputStream(f);
      module = ASFDefinitionParser.load(is);
      for (egov.appservice.asf.model.ServiceComponent c : module.getComponents()) {
        String serviceId = module.getId() + "." + c.getId();
        String svcClassName = c.getInterfaze();
        String wsdl = c.getWsdl();
        ExternalServiceDefinition serviceDefinition = new ExternalServiceDefinition(serviceId, 
          svcClassName, 
          wsdl);
        serviceDefinition.setMetaData(new ServiceMetaData(module, c));
        this.serviceRepository.addServiceDefinition(serviceId, serviceDefinition);
        ((net.risesoft.soa.asf.model.ServiceComponent)c).setId(serviceId);
        ((net.risesoft.soa.asf.model.ServiceComponent)c).setCategory(module.getId());
      }
    } catch (Throwable ex) {
      log.error("parse error: [" + f.getAbsolutePath() + "]", ex);
    } finally {
      if (is != null) try {
          is.close(); } catch (IOException localIOException2) {
        }
    }
  }

  private File[] getDefinitionFiles() {
    File path = new File(storePath);
    if (!(path.exists())) path.mkdirs();
    return path.listFiles(new FilenameFilter()
    {
      public boolean accept(File dir, String name) {
        return name.endsWith(".xml");
      }
    });
  }
}