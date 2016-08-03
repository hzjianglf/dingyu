package net.risesoft.soa.asf.util.dirwatcher;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class AbstractResourceWatcher extends IntervalThread
  implements IResourceWatcher
{
  private Collection listeners = new LinkedList();

  public AbstractResourceWatcher(int intervalSeconds, String name)
  {
    super(intervalSeconds, name);
  }

  public void removeAllListeners()
  {
    this.listeners.clear();
  }

  public void addListener(IResourceListener listener)
  {
    this.listeners.add(listener);
  }

  public void removeListener(IResourceListener listener)
  {
    this.listeners.remove(listener);
  }

  protected void resourceAdded(Object newResource)
  {
    Iterator listIt = this.listeners.iterator();

    while (listIt.hasNext())
      ((IResourceListener)listIt.next()).onAdd(newResource);
  }

  protected void resourceChanged(Object changedResource)
  {
    Iterator listIt = this.listeners.iterator();

    while (listIt.hasNext())
      ((IResourceListener)listIt.next()).onChange(changedResource);
  }

  protected void resourceDeleted(Object deletedResource)
  {
    Iterator listIt = this.listeners.iterator();

    while (listIt.hasNext())
      ((IResourceListener)listIt.next()).onDelete(deletedResource);
  }

  protected void monitoringStarted(Object monitoredResource)
  {
    Iterator listIt = this.listeners.iterator();

    while (listIt.hasNext())
      ((IResourceListener)listIt.next()).onStart(monitoredResource);
  }

  protected void monitoringStopped(Object notMonitoredResource)
  {
    Iterator listIt = this.listeners.iterator();

    while (listIt.hasNext())
      ((IResourceListener)listIt.next()).onStop(notMonitoredResource);
  }

  protected abstract void doInterval();
}