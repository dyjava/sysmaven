package com.sys.domain;

import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "node")
public class TreeNode {
	public String id="";	//绑定节点的标识值。
	public String text="";	//显示的节点文本。
	public String state="";	//节点状态，'open' 或 'closed'。
	public String iconCls="";	//显示的节点图标CSS类ID。
	public String checked="";	//该节点是否被选中。
	public String attributes="";	//绑定该节点的自定义属性。
	public String target="";	//目标DOM对象。
	public String url="" ;

	@XmlElementWrapper(name="children")
	@XmlElement(name="node")
	public List<TreeNode> children = new ArrayList<TreeNode>() ;
}
