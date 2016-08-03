package net.risesoft.soa.asf.util.dirwatcher;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DirectoryWatcher extends AbstractResourceWatcher
{
  private Map currentFiles = new HashMap();
  private String directory;
  private Map prevFiles = new HashMap();

  public DirectoryWatcher(String directoryPath, int intervalSeconds)
    throws IllegalArgumentException
  {
    super(intervalSeconds, directoryPath + " interval watcher.");

    File theDirectory = new File(directoryPath);

    if ((theDirectory != null) && (!(theDirectory.isDirectory())))
    {
      String message = "The path " + this.directory + 
        " does not represent a valid directory.";
      throw new IllegalArgumentException(message);
    }

    this.directory = directoryPath;
  }

  public static void main(String[] args)
  {
    DirectoryWatcher dw = new DirectoryWatcher("E:/cyc/", 5);
    dw.addListener(new FileListener());
    dw.start();
  }

  public void start()
  {
    takeSnapshot();

    super.start();

    File theDirectory = new File(this.directory);
    monitoringStarted(theDirectory);
  }

  public void stop()
  {
    super.stop();

    File theDirectory = new File(this.directory);
    monitoringStopped(theDirectory);
  }

  private void takeSnapshot()
  {
    this.prevFiles.clear();
    this.prevFiles.putAll(this.currentFiles);

    this.currentFiles.clear();

    File theDirectory = new File(this.directory);
    File[] children = theDirectory.listFiles();

    for (int i = 0; i < children.length; ++i)
    {
      File file = children[i];
      this.currentFiles.put(file.getAbsolutePath(), 
        new Long(file.lastModified()));
    }
  }

  protected void doInterval()
  {
    takeSnapshot();

    Iterator currentIt = this.currentFiles.keySet().iterator();

    while (currentIt.hasNext())
    {
      String fileName = (String)currentIt.next();
      Long lastModified = (Long)this.currentFiles.get(fileName);

      if (!(this.prevFiles.containsKey(fileName)))
      {
        resourceAdded(new File(fileName));
      }
      else {
        if (!(this.prevFiles.containsKey(fileName)))
          continue;
        Long prevModified = (Long)this.prevFiles.get(fileName);

        if (prevModified.compareTo(lastModified) == 0)
        {
          continue;
        }

        resourceChanged(new File(fileName));
      }

    }

    Iterator prevIt = this.prevFiles.keySet().iterator();

    while (prevIt.hasNext())
    {
      String fileName = (String)prevIt.next();

      if (this.currentFiles.containsKey(fileName))
        continue;
      resourceDeleted(fileName);
    }
  }
}