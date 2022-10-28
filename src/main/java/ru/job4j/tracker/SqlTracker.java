package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.tracker.model.Item;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private Connection cn;

    public static final Logger LOG = LoggerFactory.getLogger(SqlTracker.class.getName());

    public SqlTracker() {
    }

    public SqlTracker(Connection connection) {
        this.cn = connection;
    }

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            LOG.error("IllegalsStateException", e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (var statement = cn.prepareStatement("insert into items(name, created) values(?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            statement.execute();
            try (ResultSet genKeys = statement.getGeneratedKeys()) {
                if (genKeys.next()) {
                    item.setId(genKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOG.error("RuntimeException", e);
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        try (var statement = cn.prepareStatement("update items set name = ?, created = ? where id = ?")) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(3, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOG.error("RuntimeException", e);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (var statement = cn.prepareStatement("delete from items where id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOG.error("RuntimeException", e);
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();
        try (var statement = cn.createStatement()) {
            var selection = statement.executeQuery("select * from items");
            while (selection.next()) {
                list.add(useSelection(selection));
            }
        } catch (SQLException e) {
            LOG.error("RuntimeException", e);
        }
        return list;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();
        try (var statement = cn.prepareStatement("select * from items where name = ?")) {
            statement.setString(1, key);
            var selection = statement.executeQuery();
            while (selection.next()) {
                list.add(useSelection(selection));
            }
        } catch (SQLException e) {
            LOG.error("RuntimeException", e);
        }
        return list;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        try (var statement = cn.prepareStatement("select * from items where id = ?")) {
            statement.setInt(1, id);
            var selection = statement.executeQuery();
            if (selection.next()) {
                item = useSelection(selection);
            }
        } catch (SQLException e) {
            LOG.error("RuntimeException", e);
        }
        return item;
    }

    public Item useSelection(ResultSet selection) throws SQLException {
        return  new Item(selection.getInt("id"),
                selection.getString("name"),
                selection.getTimestamp("created").toLocalDateTime());
    }
}