package net.risesoft.soa.asf.core.ws.cxf;

import net.risesoft.soa.asf.core.ws.WSServer;
import org.apache.cxf.endpoint.Server;

public class CXFServer
  implements WSServer
{
  private Server server;

  public CXFServer(Server svr)
  {
    this.server = svr;
  }

  public void shutdown() {
    this.server.stop();
  }

  public void startup() {
    this.server.start();
  }
}