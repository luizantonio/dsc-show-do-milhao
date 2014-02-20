package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import java.text.ParseException;

import java.util.ArrayList;

import java.util.List;

import model.Usuario;

import dao.DBUtility;

public class UsuarioDao {

	private Connection connection;

	public UsuarioDao() {

		connection = DBUtility.getConnection();

	}

	public void addUser(Usuario usuario) {

		try {

			PreparedStatement preparedStatement = connection

					.prepareStatement("insert into Usuario(id,nome,senha) values (?,?, ?, ? )");

			// Parameters start with 1

			preparedStatement.setInt(1, usuario.getId());

			preparedStatement.setString(2, usuario.getNome());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public void deleteUser(int userId) {

		try {

			PreparedStatement preparedStatement = connection

			.prepareStatement("delete from Usuario where id=?");

			// Parameters start with 1

			preparedStatement.setInt(1, userId);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public void updateUser(Usuario usuario) throws ParseException {

		try {

			PreparedStatement preparedStatement = connection

			.prepareStatement("update Usuario set nome=?,senha=?" +

			"where id=?");

			// Parameters start with 1

			preparedStatement.setString(1, usuario.getNome());

			preparedStatement.setString(2, usuario.getSenha());


			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public List<Usuario> getAllUsers() {

		List<Usuario> users = new ArrayList<Usuario>();

		try {

			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery("select * from Usuario");

			while (rs.next()) {

				Usuario user = new Usuario();

				user.setId(rs.getInt("id"));

				user.setNome(rs.getString("nome"));

				user.setSenha(rs.getString("senha"));

				users.add(user);

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return users;

	}

	public Usuario getUserById(int userId) {

		Usuario user = new Usuario();

		try {

			PreparedStatement preparedStatement = connection.

			prepareStatement("select * from Usuario where id=?");

			preparedStatement.setInt(1, userId);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {

				user.setId(rs.getInt("id"));

				user.setNome(rs.getString("nome"));

				user.setSenha(rs.getString("senha"));


			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return user;

	}

}
