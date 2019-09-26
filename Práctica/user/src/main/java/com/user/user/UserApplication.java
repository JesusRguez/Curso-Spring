package com.user.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication implements CommandLineRunner{
	
	@Autowired private MapperService<userEntity, userDto> userEntityMapper;
	@Autowired private MapperService<userDto, userEntity> userDtoMapper;

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		userEntity uno = new userEntity();
		userDto dos = new userDto();
		
		uno.setNombre("Pepe");
		uno.setApellidos("Pereh");
		uno.setDni("65465416d");
		uno.setfNacimiento("02/10/1985");
		
		dos.setNombre("Pedro");
		dos.setApellidos("Diaz");
		dos.setDni("1235D");
		dos.setEdad(32);
		
		dos = userEntityMapper.map(uno);
		
		uno = userDtoMapper.map(dos);
		
		System.out.println(dos.getEdad());
	}

}
