package modelo;


public class Usuario {
	private Long id;
	private String username;
	private String pass;
	private String email;
	private Rol rol;
	
	private String errorId, errorUser, errorPass, errorEmail;
	private boolean correcto = true;

	public Usuario(Long id, String username, String pass, String email, Rol rol) {
		setId(id);
		setUsername(username);
		setPass(pass);
		setRol(rol);
	}
	
	public Usuario(Long id, String username, String pass, String email) {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if(username == null || username.trim().length() <= 3 || username.trim().length() >= 30) {
			setErrorUser("No se admiten nombres de usuario de menos de 3 caracteres");
		}
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		if(pass== null || pass.trim().length()<=6) {
			setErrorPass("No se admiten contraseñas de menos de 6 caracteres");
		}
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(email == null || !email.matches("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b")) {
			setErrorEmail("Email no válido");
		}
		this.email = email;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		correcto = false;
		this.errorId = errorId;
	}

	public String getErrorUser() {
		return errorUser;
	}

	public void setErrorUser(String errorUser) {
		correcto = false;
		this.errorUser = errorUser;
	}

	public String getErrorPass() {
		return errorPass;
	}

	public void setErrorPass(String errorPass) {
		correcto = false;
		this.errorPass = errorPass;
	}

	public String getErrorEmail() {
		return errorEmail;
	}

	public void setErrorEmail(String errorEmail) {
		correcto = false;
		this.errorEmail = errorEmail;
	}

	public boolean isCorrecto() {
		return correcto;
	}

	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}
	
	
	
	

}
