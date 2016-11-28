package ua.com.juja.garazd.sqlwebmanager.model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Scope("prototype")
public class PostgresDatabaseManager implements DatabaseManager {

    private static String JDBC_POSTGRESQL_URL = "jdbc:postgresql://localhost:5432/";
    private static String DRIVER = "org.postgresql.Driver";
    private static String USER_NAME = "postgres";
    private static String PASSWORD = "postgres";
    private static Logger logger = LogManager.getLogger(PostgresDatabaseManager.class.getName());
    private JdbcTemplate template;
    private Connection connection;

    @Override
    public void connectDatabase(String database, String user, String password) {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Please load database driver to project.");
            logger.debug("Error in the load load database driver to project " + e);
            throw new DatabaseManagerException("Error in the load load database driver to project ", e);
        }

        try {
            connection = DriverManager.getConnection(
                JDBC_POSTGRESQL_URL + database + "", "" + user + "",
                "" + password + "");
        } catch (SQLException e) {
            connection = null;
            logger.debug("Error in the method connectionDatabase do not correct values in the file configuration " + e);
            throw new DatabaseManagerException("Please enter the correct values in the file configuration.", e);
        }
    }

    @Override
    public boolean isConnected() {
        return connection != null;
    }

    @Override
    public void disconnectDatabase() {
        connection = null;
    }

    @Override
    public void createDatabase(String databaseName) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE " + databaseName);
        } catch (SQLException e) {
            logger.debug("Error in the method createDatabase " + e);
            throw new DatabaseManagerException("Error in the method createDatabase ", e);
        }
    }

    @Override
    public void dropDatabase(String databaseName) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP DATABASE " + databaseName);
        } catch (SQLException e) {
            logger.debug("Error in the method dropDatabase " + e);
            throw new DatabaseManagerException("Error in the method dropDatabase ", e);
        }
    }

    private void executeUpdate(String sql) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.debug("Error in the method executeUpdate " + e);
            throw new DatabaseManagerException("Error in the method executeUpdate ", e);
        }
    }

    @Override
    public void createTable(String query) {
        template.execute(String.format("CREATE TABLE IF NOT EXISTS %s", query));
    }

    @Override
    public void dropTable(String tableName) {
        dropSequence(tableName);
        executeUpdate(String.format("DROP TABLE IF EXISTS public.%s CASCADE", tableName));
    }

    private void dropSequence(String tableName) {
        executeUpdate(String.format("DROP SEQUENCE IF EXISTS public.%s_seq CASCADE", tableName));
    }

    @Override
    public void createEntry(String tableName, Map<String, Object> columnData) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO " + tableName + " (" + getColumnNames(columnData) + ")" +
                " VALUES (" + getColumnValues(columnData) + ")");
        } catch (SQLException e) {
            logger.debug("Error in the method createEntry " + e);
            throw new DatabaseManagerException("Error in the method createEntry ", e);
        }
    }

    @Override
    public void updateTable(String table, String primaryKeyName, String primaryKeyValue, Map<String, String> newValue) {
        String columns = StringUtils.collectionToDelimitedString(newValue.keySet(), ",", "\"", "\" = ?");

        template.update(String.format("UPDATE %s SET %s WHERE %s = %s",
            table, columns, primaryKeyName, primaryKeyValue), newValue.values().toArray());
    }

    @Override
    public void clearTable(String table) {
        template.update(String.format("DELETE FROM %s", table));
    }

    @Override
    public Set<String> getTableNames() {
        List<String> result = template.query("SELECT table_name FROM information_schema.tables " +
                "WHERE table_schema='public' AND table_type='BASE TABLE'",
            new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString("table_name");
                }
            });
        return new LinkedHashSet<>(result);
    }

    @Override
    public String getPrimaryKey(String table) {
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet resultSet = meta.getPrimaryKeys(null, null, table);
            while (resultSet.next()) {
                return resultSet.getString("COLUMN_NAME");
            }
            return null;
        } catch (SQLException e) {
            logger.debug("Error in the method getPrimaryKey " + e);
            throw new DatabaseManagerException("Error in the method getPrimaryKey ", e);
        }
    }

    @Override
    public List<Map<String, Object>> getTableData(String table) {
        return template.query("SELECT * FROM " + table,
            new RowMapper<Map<String, Object>>() {
                @Override
                public Map<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {
                    ResultSetMetaData rsmd = resultSet.getMetaData();
                    Map<String, Object> result = new LinkedHashMap<>();
                    for (int index = 0; index < rsmd.getColumnCount(); index++) {
                        result.put(rsmd.getColumnName(index + 1), resultSet.getObject(index + 1));
                    }
                    return result;
                }
            });
    }

    @Override
    public Set<String> getTableColumns(String table) {
        List<String> result = template.query("SELECT * FROM information_schema.columns " +
                "WHERE table_schema = 'public' AND table_name = ?",
            new Object[] {table},
            new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString("column_name");
                }
            });
        return new LinkedHashSet<>(result);
    }

    @Override
    public Set<String> getDatabasesName() {
        connectDatabase("", USER_NAME, PASSWORD);
        String sqlQuery = "SELECT datname FROM pg_database WHERE datistemplate = false;";
        Set<String> result = new TreeSet<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }
            return result;
        } catch (SQLException e) {
            logger.debug("Error in the method getDatabasesNames " + e);
            throw new DatabaseManagerException("Error in the method getDatabasesNames ", e);
        }
    }

    @Override
    public int getTableSize(String tableName) {
        try (Statement statement = connection.createStatement();
             ResultSet tableSize = statement.executeQuery("SELECT COUNT(*) FROM public." + tableName)) {
            tableSize.next();
            return tableSize.getInt(1);
        } catch (SQLException e) {
            logger.debug("Error in the method getDatabasesNames " + e);
            throw new DatabaseManagerException("Error in the method getDatabasesNames ", e);
        }
    }

    private String getColumnNames(Map<String, Object> columnData) {
        String keys = "";
        for (Map.Entry<String, Object> pair : columnData.entrySet()) {
            keys += pair.getKey() + ", ";
        }
        return keys.substring(0, keys.length() - 2);
    }

    private String getColumnValues(Map<String, Object> columnData) {
        String values = "";
        for (Map.Entry<String, Object> pair : columnData.entrySet()) {
            values += "'" + pair.getValue() + "', ";
        }
        return values.substring(0, values.length() - 2);
    }
}