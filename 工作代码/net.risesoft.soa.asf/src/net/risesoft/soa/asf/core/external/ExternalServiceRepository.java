package net.risesoft.soa.asf.core.external;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.risesoft.soa.asf.core.AbstractServiceRepository;
import net.risesoft.soa.asf.core.ServiceDefinition;
import net.risesoft.soa.asf.model.RepositoryInfo;
import org.slf4j.Logger;

public class ExternalServiceRepository extends AbstractServiceRepository
{
  private Map<String, ServiceDefinition> serviceDefinitions = new HashMap();

  public ExternalServiceRepository() {
    setName("asf-external-repository");
    this.repoInfo = new RepositoryInfo();
    this.repoInfo.setAlias("asf-external-repository");
    this.repoInfo.setIp("<external>");
    this.repoInfo.setPort("N/A");
    this.repoInfo.setDescription("<外部服务库>");
  }

  public void addServiceDefinition(String id, ServiceDefinition sd) {
    synchronized (this.serviceDefinitions) {
      this.serviceDefinitions.put(id, sd);
    }
    fireEvent(sd, 1);
    this.log.info(sd + " added in " + this + ".");
  }

  public void clear() {
    for (String sid : this.serviceDefinitions.keySet())
      removeServiceDefinition(sid);
  }

  public ServiceDefinition getServiceDefinition(String id)
  {
    return ((ServiceDefinition)this.serviceDefinitions.get(id));
  }

  public List<ServiceDefinition> getServiceDefinitionList() {
    List list = new ArrayList(this.serviceDefinitions.values());
    Collections.sort(list, new Comparator()
    {
      public int compare(ServiceDefinition o1, ServiceDefinition o2) {
        return o1.getServiceId().compareToIgnoreCase(o2.getServiceId());
      }
    });
    return Collections.unmodifiableList(list);
  }

  public void removeServiceDefinition(String id) {
    ServiceDefinition sd = null;
    synchronized (this.serviceDefinitions) {
      sd = (ServiceDefinition)this.serviceDefinitions.remove(id);
    }
    if (sd != null) {
      fireEvent(sd, 2);
      this.log.info(sd + " removed from " + this + ".");
    }
  }
}