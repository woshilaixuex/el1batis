package com.elyr1c.el1batis.test.xml;

import com.elyr1c.el1batis.Configuration;
import com.elyr1c.el1batis.io.Resources;
import com.elyr1c.el1batis.session.SqlSession;
import com.elyr1c.el1batis.session.SqlSessionFactory;
import com.elyr1c.el1batis.session.SqlSessionFactoryBuilder;
import com.elyr1c.el1batis.test.dao.DaoTest;
import com.elyr1c.el1batis.test.dao.IUserDao;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName XMLTest
 * @Description TODO
 * @Author Elyr1c
 * @Date 2025/6/28 22:54
 */
public class XMLTest {
    @Test
    public void TestSqlTransToJDBC() {
        String sql = "SELECT id, userId, userHead, createTime\n" +
                "        FROM user\n" +
                "        where id = #{id}";
        Pattern pattern = Pattern.compile("(#\\{(.*?)})");
        Matcher matcher = pattern.matcher(sql);
        for (int i = 1; matcher.find(); i++) {
            String g1 = matcher.group(1);
            String g2 = matcher.group(2);
            System.out.println(g1);
            System.out.println(g2);
//            String g3 = matcher.group(3);
//            System.out.println(g3);
            sql = sql.replace(g1, "?");
        }
        System.out.println(sql);
    }
    @Test
    public void TestParseXMLToSession() throws IOException {
        Reader reader = Resources.getResourceAsReader("el1batis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        String res = userDao.queryUserInfoById("10001");
    }
}
