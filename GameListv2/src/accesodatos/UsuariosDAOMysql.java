package accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Rol;
import modelo.Usuario;

public class UsuariosDAOMysql implements Dao<Usuario> {
	
	private static UsuariosDAOMysql instancia;
	// para la conexion con la base de datos
	private static final String URL = "jdbc:mysql://localhost:3306/gameslist?characterEncoding=UTF-8";
	private static final String USER = "debian-sys-maint";
	private static final String PASS = "o8lAkaNtX91xMUcV";
	
	private static final String SQLSELECT = "SELECT * FROM usuarios u JOIN roles r ON u.id_rol = r.id";
	private static final String SQL_SELECT_EMAIL = "SELECT * FROM usuarios u JOIN roles r ON u.id_rol = r.id WHERE u.email = ?";
	private static final String SQL_INSERT = "INSERT INTO usuarios(username, pass, email, id_rol) VALUES(?,?,?,?)";
	private static final String SQL_UPDATE_EMAIL = "UPDATE usuarios SET email = ? WHERE id = ?";
	private static final String SQL_UPDATE_PASS = "UPDATE usuarios SET pass = ? WHERE id = ?";
	
	static {
		try {
			// Registramos el driver de MySQL de forma EXPLÍCITA ya que en las aplicaciones
			// web lo necesitan todavía
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha podido cargar el driver de MySQL", e);
		}
	}
	private UsuariosDAOMysql() {
		
	}
	
	public static UsuariosDAOMysql getInstancia() {
		if(instancia == null) {
			instancia = new UsuariosDAOMysql();
		}
		return instancia;
	}

	@Override
	public Iterable<Usuario> obtenerTodos() {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				Statement st = conn.createStatement();
				ResultSet res = st.executeQuery(SQLSELECT)) {
			
			ArrayList<Usuario> usuarios = new ArrayList<>();
				
			while(res.next()) {
				Rol rol = new Rol(res.getLong("r.id"), res.getString("r.nombre"), res.getString("r.descripcion"));
				Usuario usuario = new Usuario(res.getLong("u.id"), res.getString("u.username"),res.getString("u.pass"), res.getString("u.email"), rol);
				usuarios.add(usuario);	
			}
			return usuarios;
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un error a la hora de obtener la información de la base de datos");
			
		}
	}

	@Override
	public Usuario obtenerPorId(Long id) {
		// TODO Auto-generated method stub
		return Dao.super.obtenerPorId(id);
	}

	@Override
	public Usuario obtenerPorEmail(String email) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = conn.prepareStatement(SQL_SELECT_EMAIL)) {
			ps.setString(1, email);
			
			try(ResultSet res = ps.executeQuery();){
				Usuario usuario = null;
				if(res.next()) {
					Rol rol = new Rol(res.getLong("r.id"), res.getString("r.nombre"), res.getString("r.descripcion"));
					usuario = new Usuario(res.getLong("u.id"), res.getString("u.username"),res.getString("u.pass"), res.getString("u.email"), rol);
				}
				
				return usuario;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No ha podido obtenerse la informacion del usuario");
		}
	}

	@Override
	public void insertar(Usuario usuario) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {
			ps.setString(1, usuario.getUsername());
			ps.setString(2, usuario.getPass());
			ps.setString(3, usuario.getEmail());
			ps.setLong(4, usuario.getRol().getId());
			int numRegistros = ps.executeUpdate();
			if(numRegistros <=0) {
				throw new AccesoDatosException("Se han insertado 0 registros");
			}else if(numRegistros > 1) {
				throw new AccesoDatosException("SE HA INSERTADO MÁS DE UN REGISTRO");
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido realizar la operacion de inserción");
		}
		
	}

	@Override
	public void modificarEmail(Usuario usuario) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_EMAIL)) {
			ps.setString(1, usuario.getEmail());
			ps.setLong(2, usuario.getId());
			int numRegistros = ps.executeUpdate();
			if(numRegistros <=0) {
				throw new AccesoDatosException("Se han modificado 0 registros");
			}else if(numRegistros > 1) {
				throw new AccesoDatosException("SE HA MODIFICADO MÁS DE UN REGISTRO");
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido realizar la modificación");
		}
	}
	
	@Override
	public void modificarPass(Usuario usuario) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_PASS)) {
			ps.setString(1, usuario.getPass());
			ps.setLong(2, usuario.getId());
			int numRegistros = ps.executeUpdate();
			if(numRegistros <=0) {
				throw new AccesoDatosException("Se han modificado 0 registros");
			}else if(numRegistros > 1) {
				throw new AccesoDatosException("SE HA MODIFICADO MÁS DE UN REGISTRO");
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido realizar la modificación");
		}
	}
	@Override
	public void borrar(Long id) {
		// TODO Auto-generated method stub
		Dao.super.borrar(id);
	}
	
	

}
