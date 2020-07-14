package modelo;

public class Usuario {
private int id_usuario;
private String password;
private String nickname;
private String rol;

public Usuario() {
	this.nickname="";
}

public Usuario(int id_usuario, String password, String nickname, String rol) {
	super();
	this.id_usuario = id_usuario;
	this.password = password;
	this.nickname = nickname;
	this.rol = rol;
}
public int getId_usuario() {
	return id_usuario;
}
public void setId_usuario(int id_usuario) {
	this.id_usuario = id_usuario;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public String getRol() {
	return rol;
}
public void setRol(String rol) {
	this.rol = rol;
}


}
