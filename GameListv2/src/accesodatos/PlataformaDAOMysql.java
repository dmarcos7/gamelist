package accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import modelo.Plataforma;

public class PlataformaDAOMysql implements Dao<Plataforma>{
	
	private static PlataformaDAOMysql instancia;
	private static final String URL = "jdbc:mysql://localhost:3306/gameslist?characterEncoding=UTF-8";
	private static final String USER = "debian-sys-maint";
	private static final String PASS = "o8lAkaNtX91xMUcV";
	
	private static String SELECT_SQL = "SELECT * FROM plataformas";
	private static String SELECT_ID = "SELECT * FROM plataformas WHERE id = ?";
	
	static {
		try {
			// Registramos el driver de MySQL de forma EXPLÍCITA ya que en las aplicaciones
			// web lo necesitan todavía
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha podido cargar el driver de MySQL", e);
		}
	}
	
	private PlataformaDAOMysql() {
		
	}
	
	public static PlataformaDAOMysql getInstancia() {
		if(instancia==null) {
			instancia = new PlataformaDAOMysql();
		}
		return instancia;
	}
	
	@Override
	public Iterable<Plataforma> obtenerTodos() {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				Statement st = conn.createStatement();
				ResultSet res = st.executeQuery(SELECT_SQL)) {
			ArrayList<Plataforma> plataformas = new ArrayList<>();
			Plataforma plataforma = null;
			while(res.next()) {
				plataforma = new Plataforma(res.getInt("id"), res.getString("nombre"));
				plataformas.add(plataforma);
			}
			
			return plataformas;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se han podido obtener los generos");
		}
	}

	@Override
	public Plataforma obtenerPorId(Long id) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = conn.prepareStatement(SELECT_ID)) {
			ps.setLong(1, id);
			ResultSet res = ps.executeQuery();
			Plataforma plataforma = null;
			if(res.next()) {
				plataforma = new Plataforma(res.getInt("id"), res.getString("nombre"));
			}
			return plataforma;
		} catch (SQLException e) {
			
			throw new AccesoDatosException("No se han podido obtener los generos");
		}
	}
	

}
