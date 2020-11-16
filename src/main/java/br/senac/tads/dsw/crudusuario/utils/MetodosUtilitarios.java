package br.senac.tads.dsw.crudusuario.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
@Service
public class MetodosUtilitarios {

	public static String getDataHora() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
		Date data = new Date();  
		
		return dateFormat.format(data);
		
		
	}
}
