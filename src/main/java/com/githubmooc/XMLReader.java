package com.githubmooc;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.sql.*;
import java.util.*;

public class XMLReader {
    public static void main(String[] args) throws Exception {

//        加载驱动
        Class.forName("org.sqlite.JDBC");
        String db = "db/color.db";
//        获取连接
        Connection conn = DriverManager.getConnection("jdbc:sqlite:" + db);
//        创建PreparedStatement对象
        PreparedStatement preparedStatement = conn.prepareStatement("insert into color values(?,?,?)");

//        获取解析器对象
        SAXReader reader = new SAXReader();
//        获取文档对象
        Document document = reader.read("input/aaa.html");
        //获取跟节点<ul>
        Element root = document.getRootElement();
//        获取跟节点下所有的<li>节点
        List<Element> lis = root.elements("li");

//        定义<li>节点下<div>节点
        Element div;
//        定义<div>节点下<a>节点
        Element a;
//        定义<a>节点下的<span>所有节点
        List<Element> spans;
        String name;
        String pinyin;
        String rgb;

        for (Element li : lis) {
            div = li.element("div");
            a = div.element("a");
            spans = a.elements("span");

            name = spans.get(0).getText();
            pinyin = spans.get(1).getText();
            rgb = spans.get(2).getText();

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pinyin);
            preparedStatement.setString(3, rgb);

//            执行SQL
            boolean execute = preparedStatement.execute();
            System.out.println(execute);
        }

//        关闭资源，未进行费控判断，写法不标准
        preparedStatement.close();
        conn.close();
    }
}
