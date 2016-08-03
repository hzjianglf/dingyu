package net.risesoft.soa.asf.egov;

import egov.appservice.asf.service.RepositoryManager;
import java.util.List;
import net.risesoft.soa.asf.core.ServiceRepositoryHolder;
import net.risesoft.soa.asf.core.remote.RemoteServiceRepository;
import net.risesoft.soa.asf.egov.util.RepoStoreUtil;
import net.risesoft.soa.asf.model.RepositoryInfo;

public class RepositoryManagerImpl
  implements RepositoryManager
{
  public void registerRepository(String name, String ip, int port, String basePath, String description)
  {
    RepositoryInfo repo = new RepositoryInfo();
    repo.setAlias(name);
    repo.setDescription(description);
    repo.setIp(ip);
    repo.setPort(String.valueOf(port));
    repo.setBasePath(basePath);
    List repos = RepoStoreUtil.load();
    repos.add(repo);
    RepoStoreUtil.save(repos);
    ServiceRepositoryHolder.putRepository(new RemoteServiceRepository(repo.getIp(), 
      repo.getPort(), 
      repo.getBasePath(), 
      repo.getAlias(), 
      repo.getDescription()));
  }

  public void removeRepository(String name) {
    List<RepositoryInfo> repos = RepoStoreUtil.load();
    for (RepositoryInfo ri : repos) {
      if (name.equals(ri.getAlias())) {
        repos.remove(ri);
        break;
      }
    }
    RepoStoreUtil.save(repos);
  }
}