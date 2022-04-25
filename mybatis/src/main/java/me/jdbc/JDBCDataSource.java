package me.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import lombok.extern.slf4j.Slf4j;
import me.dslztx.assist.util.ObjectAssist;

@Slf4j
public class JDBCDataSource {

    private static String driver = "com.mysql.cj.jdbc.Driver";

    private static String url = "jdbc:mysql://127.0.0.1:3306/indb?characterEncoding=utf-8";
    private static String user = "root";
    private static String pwd = "root";

    private static DataSource dataSource = null;

    static public Connection getConnection() {
        try {
            if (dataSource == null) {
                BasicDataSource basicDataSource = new BasicDataSource();
                basicDataSource.setDriverClassName(driver);
                basicDataSource.setUrl(url);
                basicDataSource.setUsername(user);
                basicDataSource.setPassword(pwd);

                dataSource = basicDataSource;
            }

            return dataSource.getConnection();
        } catch (Exception e) {
            log.error("", e);

            return null;
        }
    }

    public static void main(String[] args) throws SQLException {
        jdbcQuery();
        // jdbcInsert();
    }

    public static void jdbcQuery() {

        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;

        try {
            connection = getConnection();

            statement = connection.createStatement();

            String sql = "select * from user";

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");

                log.info("fetch user: [id {}, name {}, age {}]", id, name, age);
            }
        } catch (Exception e) {
            e.printStackTrace();

            log.error("", e);
        } finally {
            try {
                if (ObjectAssist.isNotNull(resultSet)) {
                    resultSet.close();
                }
                if (ObjectAssist.isNotNull(statement)) {
                    statement.close();
                }
                if (ObjectAssist.isNotNull(connection)) {
                    connection.close();
                }
            } catch (Exception ignore) {
            }
        }
    }

    public static void jdbcInsert() throws SQLException {

        Statement statement = null;
        Connection connection = null;

        try {
            connection = getConnection();

            connection.setAutoCommit(false);

            statement = connection.createStatement();

            String sql1 = "insert into User(`id`,`name`,`age`) values(3,'dslztx',33)";

            statement.executeUpdate(sql1);

            String sql2 = "insert into User(`id`,`name`,`age`) values(2,'lmh','error')";
            statement.executeUpdate(sql2);

            connection.commit();
        } catch (Exception e) {
            connection.rollback();

            log.error("rollback", e);
        } finally {
            try {
                if (ObjectAssist.isNotNull(statement)) {
                    statement.close();
                }
                if (ObjectAssist.isNotNull(connection)) {
                    statement.close();
                }
            } catch (Exception ignore) {
            }
        }
    }
}
