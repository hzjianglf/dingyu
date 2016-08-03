package net.risesoft.soa.asf.util.dirwatcher;

public abstract class IntervalThread
  implements Runnable
{
  private boolean active = false;

  private int interval = -1;
  private String name;
  private Thread runner;

  public IntervalThread(int intervalSeconds, String name)
  {
    this.interval = (intervalSeconds * 1000);
    this.name = name;
  }

  public void start()
  {
    this.active = true;

    if ((this.runner == null) && (this.interval > 0)) {
      this.runner = new Thread(this);
      this.runner.start();
    }
  }

  public void stop()
  {
    this.active = false;
  }

  public void run()
  {
    Thread.currentThread().setPriority(1);

    while (this.active)
      try {
        doInterval();
        Thread.sleep(this.interval);
      }
      catch (InterruptedException localInterruptedException)
      {
      }
  }

  public String toString()
  {
    return this.name;
  }

  protected abstract void doInterval();
}