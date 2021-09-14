package com.githubmooc;

import java.sql.*;

public class GetData {
    public static void main(String[] args) throws Exception {
        //        加载驱动
        Class.forName("org.sqlite.JDBC");
        String db = "db/color.db";
//        获取连接
        Connection conn = DriverManager.getConnection("jdbc:sqlite:" + db);
//        创建PreparedStatement对象
        PreparedStatement preparedStatement = conn.prepareStatement("select name,pinyin,rgb from color");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String pinyin = resultSet.getString("pinyin");
            String rgb = resultSet.getString("rgb");
            System.out.println("颜色名：" + name + "，拼音：" + pinyin + "，RGB：" + rgb + "。");
        }
        resultSet.close();
        preparedStatement.close();
        conn.close();
    }
}
