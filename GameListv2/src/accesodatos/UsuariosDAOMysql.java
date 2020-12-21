package accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Rol;
import modelo.Usuario;

public class UsuariosDAOMysql implements Dao<Usuario> {
	
	private static UsuariosDAOMysql instancia;
	// para la conexion con la base de datos
	private static final String URL = "jdbc:mysql://localhost:3306/peliculas_bdd?characterEncoding=UTF-8";
	private static final String USER = "debian-sys-maint";
	private static final String PASS = "o8lAkaNtX91xMUcV";
	
	private static final String SQLSELECT = "SELECT * FROM usuarios u JOIN roles r ON u.id_rol = r.id";
	
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
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return Dao.super.obtenerTodos();
	}

	@Override
	public Usuario obtenerPorId(Long id) {
		// TODO Auto-generated method stub
		return Dao.super.obtenerPorId(id);
	}

	@Override
	public Usuario obtenerPorEmail(String email) {
		// TODO Auto-generated method stub
		return Dao.super.obtenerPorEmail(email);
	}

	@Override
	public void insertar(Usuario usuario) {
		// TODO Auto-generated method stub
		Dao.super.insertar(usuario);
	}

	@Override
	public void modificar(Usuario usuario) {
		// TODO Auto-generated method stub
		Dao.super.modificar(usuario);
	}

	@Override
	public void borrar(Long id) {
		// TODO Auto-generated method stub
		Dao.super.borrar(id);
	}
	
	

}
