package net.risesoft.soa.asf.core.experimental;

import java.util.LinkedList;
import java.util.List;
import net.risesoft.soa.asf.core.ServiceProcessor;

public class ApplicationServiceContext
{
  private static final String[] processors = { 
    "net.risesoft.soa.core.local.LocalServiceProcessor", 
    "net.risesoft.soa.core.filesystem.FileSystemServiceProcessor", 
    "net.risesoft.soa.core.remote.RemoteServiceProcessor" };

  private List<ServiceProcessor> svcProcessors = new LinkedList();

  public void startup() {
    for (String processor : processors)
      try {
        Class pClass = Class.forName(processor);
        if ((pClass != null) && (ServiceProcessor.class.isAssignableFrom(pClass))) {
          ServiceProcessor p = (ServiceProcessor)pClass.newInstance();
          registerServiceProcessor(p);
          p.refresh();
        }
      }
      catch (ClassNotFoundException ex) {
        ex.printStackTrace();
      } catch (InstantiationException ex) {
        ex.printStackTrace();
      } catch (IllegalAccessException ex) {
        ex.printStackTrace();
      }
  }

  public void shutdown()
  {
  }

  public void registerServiceProcessor(ServiceProcessor sp)
  {
    if (this.svcProcessors.contains(sp)) return; this.svcProcessors.add(sp);
  }

  public void removeServiceProcessor(ServiceProcessor sp) {
    if (!(this.svcProcessors.contains(sp))) return; this.svcProcessors.remove(sp);
  }
}