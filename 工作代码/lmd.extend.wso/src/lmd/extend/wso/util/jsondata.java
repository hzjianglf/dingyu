package lmd.extend.wso.util;

import java.util.ArrayList;
import java.util.List;

public class jsondata {
	public String id;
    public String text;
    public String url;
    public String leaf;
    public List<jsondata> children=new ArrayList<jsondata>();//存放子节点
}
