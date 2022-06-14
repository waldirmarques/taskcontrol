package br.com.taskcontrol.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString(exclude = {"password"})
public class CredentialsDTO implements Serializable{

	private static final long serialVersionUID = -1257289539968588143L;

	private String email;

	private String password;
	
}
