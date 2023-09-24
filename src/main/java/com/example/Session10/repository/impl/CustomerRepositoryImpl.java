package com.example.Session10.repository.impl;

import com.example.Session10.model.Customer;
import com.example.Session10.repository.CustomerRepository;
import com.example.Session10.until.MySqlConnect;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = MySqlConnect.open();
            cs = conn.prepareCall("CALL sp_customers_select()");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getString("id"));
                c.setName(rs.getString("name"));
                c.setAge(rs.getInt("age"));
                c.setBirthday(new Date(rs.getTimestamp("birthday").getTime()));
                c.setAvatar(rs.getString("avatar"));
                customers.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                cs.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return customers;
    }

    @Override
    public Customer findId(String id) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = MySqlConnect.open();
            cs = conn.prepareCall("CALL sp_customers_select_byId(?)");
            cs.setString(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getString("id"));
                c.setName(rs.getString("name"));
                c.setAge(rs.getInt("age"));
                c.setBirthday(new Date(rs.getTimestamp("birthday").getTime()));
                c.setAvatar(rs.getString("avatar"));
                return c;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Customer c) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = MySqlConnect.open();
            cs = conn.prepareCall("CALL sp_customers_select_insert(?,?,?,?,?)");
            cs.registerOutParameter(1, Types.VARCHAR, 36);
            cs.setString(2, c.getName());
            cs.setInt(3, c.getAge());
            cs.setTimestamp(4, new Timestamp(c.getBirthday().getTime()));
            cs.setString(5, c.getAvatar());
            int result = cs.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                cs.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void edit(Customer c) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = MySqlConnect.open();
            cs = conn.prepareCall("CALL sp_customers_select_update(?,?,?,?,?)");
            cs.setString(1, c.getId());
            cs.setString(2, c.getName());
            cs.setInt(3, c.getAge());
            cs.setTimestamp(4, new Timestamp(c.getBirthday().getTime()));
            cs.setString(5, c.getAvatar());
            int result = cs.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        Connection connection = null;
        CallableStatement cs = null;
        try {
            connection = MySqlConnect.open();
            cs = connection.prepareCall("CALL sp_customers_select_delete(?)");
            cs.setString(1, id);
            int result = cs.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                cs.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public List<Customer> findName(String name) {
        Connection cn = null;
        CallableStatement cs = null;
        List<Customer> data = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            return data;
        }
        try {
            cn = MySqlConnect.open();
            assert cn != null;
            cs = cn.prepareCall("CALL sp_customers_select_byName(?)");
            cs.setString(1, "%" + name + "%");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getString("id"));
                c.setName(rs.getString("name"));
                c.setAge(rs.getInt("age"));
                c.setBirthday(new Date(rs.getTimestamp("birthday").getTime()));
                c.setAvatar(rs.getString("avatar"));
                data.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                cs.close();
                cn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return data;
    }
}
