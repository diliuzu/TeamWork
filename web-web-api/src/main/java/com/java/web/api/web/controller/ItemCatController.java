package com.java.web.api.web.controller;

import com.java.commons.util.BaseResult;
import com.java.commons.util.MapperUtils;
import com.java.commons.util.MyTree;
import com.java.domain.ItemCat;
import com.java.web.api.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("itemcat")
public class ItemCatController {

    @Autowired
    ItemCatService itemCatService;

    @GetMapping("all")
    public BaseResult getAll(HttpServletRequest request){
        List<ItemCat> itemCats = itemCatService.selectAllCategory();
        MyTree<ItemCat> myTree=new MyTree<ItemCat>();
        ItemCat itemCat=new ItemCat();
        itemCat.setId(0L);
        myTree.setNode(itemCat);
        Map<Long,MyTree> map=new HashMap<Long, MyTree>();
        itemTest(itemCats,myTree,map);
        Map<String,Object> result=new HashMap<>();
        result.put("treeMap",map);
        result.put("myTree",myTree);
        request.getServletContext().setAttribute("treeMap",map);
        String s = MapperUtils.mapToJson(result);
        System.out.println(s);

        return BaseResult.success("chenggong",result);
    }

    public void itemTest(List<ItemCat> list, MyTree<ItemCat> myTree, Map<Long,MyTree> map) {
        for (int i = 0; i < list.size();) {
            ItemCat itemCat=list.get(i);
            if (itemCat.getParentItemCat().getId().equals(myTree.getNode().getId())) {
                MyTree<ItemCat> child = new MyTree<ItemCat>();
                child.setParent(myTree);
                child.setNode(itemCat);
                myTree.getChild().add(child);
                list.remove(itemCat);

            } else {
                map.put(myTree.getNode().getId(),myTree);
                for (MyTree child : myTree.getChild()) {
                    itemTest(list, child,map);
                }
                return;
            }

        }
    }

}
