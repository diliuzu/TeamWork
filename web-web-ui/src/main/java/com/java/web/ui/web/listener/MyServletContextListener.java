package com.java.web.ui.web.listener;

import com.java.commons.util.HttpClientUtils;
import com.java.commons.util.MapperUtils;
import com.java.commons.util.MyTree;
import com.java.domain.ItemCat;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.Map;

public class MyServletContextListener implements InitializingBean, ServletContextAware {
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        String s = HttpClientUtils.doGet("http://localhost:8080/itemcat/all");
        try {
            MyTree myTree = MapperUtils.json2pojoByTree(s, "myTree", MyTree.class);
            Map<String, MyTree> treeMap = MapperUtils.json2mapByTree(s, "treeMap", MyTree.class);
            servletContext.setAttribute("treeMap",treeMap);
            servletContext.setAttribute("myTree", myTree);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
