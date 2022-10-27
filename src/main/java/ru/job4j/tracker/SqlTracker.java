package ru.job4j.tracker;

import ru.job4j.tracker.action.*;
import ru.job4j.tracker.input.ConsoleInput;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.ValidateInput;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.output.ConsoleOutput;
import ru.job4j.tracker.output.Output;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private Connection cn;

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
            throw new IllegalStateException(e);
        }
    }


    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) throws SQLException {
        try (PreparedStatement statement = cn.prepareStatement("insert into items(name, created) values(?, ?)")) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            statement.execute();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) throws SQLException {
        boolean result;
        try (PreparedStatement statement = cn.prepareStatement("update items set name = ? where id = ?")) {
            statement.setString(1, item.getName());
            statement.setInt(2, id);
            result = statement.executeUpdate() > 0;
        }
        return result;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean result;
        try (PreparedStatement statement = cn.prepareStatement("delete from items where id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        }
        return result;
    }

    @Override
    public List<Item> findAll() throws SQLException {
        List<Item> list = new ArrayList<>();
        try (var statement = cn.createStatement()) {
            var selection = statement.executeQuery("select * from items");
            while (selection.next()) {
                list.add(new Item(selection.getInt("id"),
                        selection.getString("name"),
                        selection.getTimestamp("created").toLocalDateTime())
                );
            }
        }
        return list;
    }

    @Override
    public List<Item> findByName(String key) throws SQLException {
        List<Item> list = new ArrayList<>();
        try (var statement = cn.createStatement()) {
            var selection = statement.executeQuery(String.format("select * from items where name = '%s'", key));
            while (selection.next()) {
                list.add(new Item(selection.getInt("id"),
                        selection.getString("name"),
                        selection.getTimestamp("created").toLocalDateTime())
                );
            }
        }
        return list;
    }

    @Override
    public Item findById(int id) throws SQLException {
        Item item = null;
        try (var statement = cn.createStatement()) {
            var selection = statement.executeQuery(String.format("select * from items where id = %s", id));
            while (selection.next()) {
                item = new Item(selection.getInt("id"),
                        selection.getString("name"),
                        selection.getTimestamp("created").toLocalDateTime());
            }
        }
        return item;
    }

    public static void main(String[] args) {
        Input input = new ValidateInput(
                new ConsoleInput()
        );
        Output output = new ConsoleOutput();
        try (SqlTracker tracker = new SqlTracker()) {
            tracker.init();
            List<UserAction> actions = List.of(
                    new CreateAction(output),
                    new ReplaceAction(output),
                    new DeleteAction(output),
                    new FindAllAction(output),
                    new FindByIdAction(output),
                    new FindByNameAction(output),
                    new ExitAction()
            );
            new StartUI().init(input, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}