package com.app.preparatec2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Clase que contiene la información de la pantalla de acerca.
 */
public class About_Screen extends Activity {

	public static final String copyright  = "\u00a9"; // símbolo copyright

	private TextView legendLabel, authorsLabel;
	private String legendString, authorsString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);

		legendLabel = (TextView)findViewById(R.id.textView1);
		authorsLabel = (TextView)findViewById(R.id.textView2);

		legendString = "PreparaTEC es una aplicación móvil que permite al estudiante, prepararse para la prueba de aptitud académica del "
				+ "Tecnológico de Costa Rica. La información está basada en las guías del departamento de Admisión y Registro, con el fin de brindar "
				+ "al estudiante una noción sobre la naturaleza de los ítems de dicha prueba. "+ "Este es un proyecto elaborado por Go Touch";
		
		authorsString =  "Desarrolladores: \n Jose Ariel Arias Méndez \n Edwin Vásquez Leiva \n José Pablo Fernández Cordero \n" +
		" Luis Alejandro Corrales Navarro \n Gabriela Delgado Quesada \n" + copyright + " 2014-2016 Go Touch \n Todos los derechos reservados";

		legendLabel.setTextColor(Color.parseColor("#414042"));
		legendLabel.setText(legendString);
		
		authorsLabel.setTextColor(Color.parseColor("#414042"));
		authorsLabel.setText(authorsString);
	}
}
