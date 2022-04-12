package me.mybatis.dao;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.dslztx.assist.client.mysql.DataSourceFactory;
import me.mybatis.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContextTest.xml")
public class UserDaoTest {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDao userDao;

    @Autowired
    UserDao2 userDao2;

    @Autowired
    UserDao3 userDao3;

    public void initDB0(DataSource dataSource, long id) {
        try {
            Connection connection = dataSource.getConnection();

            Statement statement = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS `User` (" + "`id` int(11) NOT NULL,"
                + "`name` varchar(255) DEFAULT NULL," + "`age` int(11) DEFAULT 0," + "PRIMARY KEY (`id`)" + ")";
            statement.execute(sql);

            String sql2 = "insert ignore into User (id, name, age) values (" + id + ",'dslztx',33)";
            statement.execute(sql2);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    @Before
    public void initDB() {
        try {
            initDB0(DataSourceFactory.obtainDataSource("in"), 1);
            initDB0(DataSourceFactory.obtainDataSource("out"), 2);
            initDB0(dataSource, 3);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    @Test
    public void test1() {
        try {
            User user = userDao.queryUserById(1L);

            Assert.assertTrue(user.getName().equals("dslztx"));
            Assert.assertTrue(user.getAge() == 33);
            Assert.assertTrue(user.getId() == 1L);
        } catch (Exception e) {
            logger.error("", e);

            Assert.fail();
        }
    }

    @Test
    public void test2() {
        try {
            User user = userDao2.queryUserById(2L);

            Assert.assertTrue(user.getName().equals("dslztx"));
            Assert.assertTrue(user.getAge() == 33);
            Assert.assertTrue(user.getId() == 2L);
        } catch (Exception e) {
            logger.error("", e);

            Assert.fail();
        }
    }

    @Test
    public void test3() {
        try {
            User user = userDao3.queryUserById(3L);

            Assert.assertTrue(user.getName().equals("dslztx"));
            Assert.assertTrue(user.getAge() == 33);
            Assert.assertTrue(user.getId() == 3L);
        } catch (Exception e) {
            logger.error("", e);

            Assert.fail();
        }
    }
}
