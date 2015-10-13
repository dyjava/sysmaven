package com.sys.spring.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sys.spring.tree.domain.TreeNode;
@Controller
public class TreeControler extends BaseController{

	@RequestMapping("tree_data")
	public void contact_list(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Object contact) {
		
		List<TreeNode> list = getTreeList("文件",5) ;
		this.printOut(list, response);
	}
	
	private List<TreeNode> getTreeList(String name, int size){

		List<TreeNode> list = new ArrayList<TreeNode>() ;
		for(int i=0;i<size;i++){
			TreeNode tree = new TreeNode() ;
			tree.id=i+"" ;
			tree.text = name+":"+(i+1) ;
			
			int s = size - (int)(Math.random()*3) -1 ;
			if(s>0){
				tree.children = getTreeList(name+"_c",s) ;
			}
			
			list.add(tree) ;
		}
		return list ;
	}
}
