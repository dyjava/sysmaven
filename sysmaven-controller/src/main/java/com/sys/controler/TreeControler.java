package com.sys.controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sys.common.ReadLocalFile;
import com.sys.common.XmlUtils;
import com.sys.domain.TreeNode;

@Controller
@RequestMapping(value = "/tree")
public class TreeControler extends BaseController{

	@RequestMapping("list")
	public void contact_list(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Object contact) {
		TreeNode node = getDate() ;
		List<TreeNode> list = new ArrayList<TreeNode>() ;
		list.add(node) ;
		this.printOut(list, response);
	}
	
	public TreeNode getDate(){
		try {
			String xml = ReadLocalFile.readLocalFile3("/props/left_tree.xml") ;
			TreeNode node = XmlUtils.xml2obj(xml, TreeNode.class) ;
			return node ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new TreeNode() ;
	}
	
}
