package ddd.controllers;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class WelcomeController {
    private static final String CONTENT_TYPE =
            "text/html; charset=utf-8";
    public static Connection conn;

    @RequestMapping(value = "/", method = RequestMethod.GET)

    public String usersAndRoles() throws SQLException, ClassNotFoundException {
        return "usersAndRoles";
    }

    @RequestMapping(value = "roles", method = RequestMethod.GET)

    public String roles(Model model) throws SQLException, ClassNotFoundException {
        this.conn = new DBConnection().getConnection();
        model.addAttribute("listR", new Gson().toJson(returnListRoles()));
        return "roles";
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)

    public String users(Model model) throws SQLException, ClassNotFoundException {
        this.conn = new DBConnection().getConnection(); // соединение с бд
        model.addAttribute("listUser", new Gson().toJson(returnListUsers()));
        model.addAttribute("listRole", new Gson().toJson(returnListRoles()));
        return "users";
    }

    @RequestMapping(value = "addUserTable", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<List<User>> addUserTable(@RequestBody User user) throws SQLException {//PathVariable

        String id_roles = user.getId_roles();
        PreparedStatement statement1 = conn.prepareStatement("insert into usersandroles.users (name_user,email,id_roles) values(?,?,?)");

        statement1.setString(1, user.getName_user());
        statement1.setString(2, user.getEmail());
        statement1.setString(3, id_roles);

        statement1.executeUpdate();

        return new ResponseEntity<>(returnListUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "addRole", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<List<Role>> addRole(@RequestBody Role role) throws SQLException {//PathVariable

        String name_roles = role.getName_roles();
        PreparedStatement statement1 = conn.prepareStatement("insert into usersandroles.roles (name_roles) values(?)");

        statement1.setString(1, name_roles);
        statement1.executeUpdate();

        return new ResponseEntity<>(returnListRoles(), HttpStatus.OK);
    }

    @RequestMapping(value = "selectRole", method = RequestMethod.POST)
    public
    @ResponseBody
    Role selectRole(@RequestBody Role role) throws SQLException {//PathVariable

        String id_roles = role.getId_roles();
        System.out.println(id_roles);
        PreparedStatement statement = conn.prepareStatement("select name_roles from usersandroles.roles where id_roles = " + id_roles);
        ResultSet resSet = statement.executeQuery();
        resSet.next();
        String name = resSet.getString("name_roles");
        role.setName_roles(name);
        System.out.println(name);
        return role;
    }

    @RequestMapping(value = "saveChange", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<List<User>> saveChange(@RequestBody User user) throws SQLException {//PathVariable

        String selectUserChang = user.getId_user();
        String name = user.getName_user();
        String email = user.getEmail();
        String id_roles = user.getId_roles();
        System.out.println(selectUserChang + "  " + name + "  " + email + "  " + id_roles);
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE  usersandroles.users  set name_user = ?, email = ?, id_roles = ? where id_user =?");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, id_roles);
        preparedStatement.setString(4, selectUserChang);
        preparedStatement.executeUpdate();

        return new ResponseEntity<>(returnListUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "changeRole", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<List<Role>> changeRole(@RequestBody Role role) throws SQLException {//PathVariable

        String id_roles = role.getId_roles();
        String name_roles = role.getName_roles();
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE  usersandroles.roles  set name_roles = ? where id_roles =?");
        preparedStatement.setString(1, name_roles);
        preparedStatement.setString(2, id_roles);
        preparedStatement.executeUpdate();

        return new ResponseEntity<>(returnListRoles(), HttpStatus.OK);
    }

    @RequestMapping(value = "delUser", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<List<User>> delUser(@RequestBody User user) throws SQLException {

        String selectUserDel = user.getId_user();
        PreparedStatement preparedStatement = conn.prepareStatement("Delete from usersandroles.users where id_user = ?");
        preparedStatement.setString(1, selectUserDel);
        preparedStatement.executeUpdate();
        return new ResponseEntity<>(returnListUsers(), HttpStatus.OK);
    }


    @RequestMapping(value = "deleteRole", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<List<Role>> deleteRole(@RequestBody Role role) throws SQLException {

        String id_roles = role.getId_roles();
        PreparedStatement preparedStatement = conn.prepareStatement("Delete from usersandroles.roles where id_roles = ?");
        preparedStatement.setString(1, id_roles);
        preparedStatement.executeUpdate();
        return new ResponseEntity<>(returnListRoles(), HttpStatus.OK);
    }

    public List<User> returnListUsers() throws SQLException {
        PreparedStatement preparedStatement;
        List<User> listUser = new ArrayList<User>();
        preparedStatement = conn.prepareStatement("select * from usersandroles.users");
        ResultSet resSet = preparedStatement.executeQuery();
        while (resSet.next()) {
            listUser.add(new User(resSet.getString("id_user"), resSet.getString("name_user"), resSet.getString("email"), resSet.getString("id_roles")));
        }
        return listUser;
    }

    public List<Role> returnListRoles() throws SQLException {
        List<Role> listRole = new ArrayList<Role>();
        PreparedStatement preparedStatement;
        preparedStatement = conn.prepareStatement("select * from usersandroles.roles");
        ResultSet resSet1 = preparedStatement.executeQuery();
        while (resSet1.next()) {
            listRole.add(new Role(resSet1.getString("id_roles"), resSet1.getString("name_roles")));
        }
        return listRole;
    }
}