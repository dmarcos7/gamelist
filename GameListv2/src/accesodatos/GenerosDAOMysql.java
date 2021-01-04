package accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Genero;

public class GenerosDAOMysql implements Dao<Genero>{
	
	private static GenerosDAOMysql instancia;
	private static final String URL = "jdbc:mysql://localhost:3306/gameslist?characterEncoding=UTF-8";
	private static final String USER = "debian-sys-maint";
	private static final String PASS = "o8lAkaNtX91xMUcV";
	
	private static String SELECT_SQL = "SELECT * FROM generos";
	private static String SELECT_ID = "SELECT * FROM generos WHERE id = ?";
	private GenerosDAOMysql() {
		
	}
	
	static {
		try {
			// Registramos el driver de MySQL de forma EXPLÍCITA ya que en las aplicaciones
			// web lo necesitan todavía
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha podido cargar el driver de MySQL", e);
		}
	}
	
	public static GenerosDAOMysql getInstancia() {
		if(instancia == null) {
			instancia = new GenerosDAOMysql();
		}
		return instancia;
	}

	@Override
	public Iterable<Genero> obtenerTodos() {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				Statement st = conn.createStatement();
				ResultSet res = st.executeQuery(SELECT_SQL)) {
			ArrayList<Genero> generos = new ArrayList<>();
			Genero genero = null;
			while(res.next()) {
				genero = new Genero(res.getInt("id"), res.getString("nombre"));
				generos.add(genero);
			}
			
			return generos;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se han podido obtener los generos");
		}
	}

	@Override
	public Genero obtenerPorId(Long id) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = conn.prepareStatement(SELECT_ID)) {
			ps.setLong(1, id);
			ResultSet res = ps.executeQuery();
			Genero genero = null;
			if(res.next()) {
				genero = new Genero(res.getInt("id"), res.getString("nombre"));
			}
			return genero;
		} catch (SQLException e) {
			
			throw new AccesoDatosException("No se han podido obtener los generos");
		}
		
	}
	
	
	
	

}
