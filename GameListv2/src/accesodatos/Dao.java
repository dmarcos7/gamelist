package accesodatos;


public interface Dao<T> {
	public static final String OPERACIÓN_NO_IMPLEMENTADA = "Operación no implementada";

	default Iterable<T> obtenerTodos() {
		throw new AccesoDatosException(OPERACIÓN_NO_IMPLEMENTADA);
	}

	default T obtenerPorId(Long id) {
		throw new AccesoDatosException(OPERACIÓN_NO_IMPLEMENTADA);
	}
	
	default T obtenerPorEmail(String email) {
		throw new AccesoDatosException(OPERACIÓN_NO_IMPLEMENTADA);
	}

	default void insertar(T objeto) {
		throw new AccesoDatosException(OPERACIÓN_NO_IMPLEMENTADA);
	}

	default void modificar(T objeto) {
		throw new AccesoDatosException(OPERACIÓN_NO_IMPLEMENTADA);
	}

	default void borrar(Long id) {
		throw new AccesoDatosException(OPERACIÓN_NO_IMPLEMENTADA);
	}
	
	default void modificarEmail(T objeto) {
		throw new AccesoDatosException(OPERACIÓN_NO_IMPLEMENTADA);
	}
	
	default void modificarPass(T objeto) {
		throw new AccesoDatosException(OPERACIÓN_NO_IMPLEMENTADA);
	}
}
