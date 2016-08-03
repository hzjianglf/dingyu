package net.risesoft.soa.asf.util.dirwatcher;

public abstract interface IResourceListener
{
  public abstract void onStart(Object paramObject);

  public abstract void onStop(Object paramObject);

  public abstract void onAdd(Object paramObject);

  public abstract void onChange(Object paramObject);

  public abstract void onDelete(Object paramObject);
}