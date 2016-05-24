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

		legendString = "PreparaTEC es una herramienta informática que permite al estudiante prepararse para la prueba de aptitud académica del "
				+ "Tecnológico de Costa Rica. La aplicación contendrá información basada en las guías de preparación, con el fin de brindar "
				+ "al estudiante una noción sobre la naturaleza de los ítems de dicha prueba.";
		
		authorsString =  "Desarrolladores: \n Jose Ariel Arias Méndez \n Edwin Vásquez Leiva \n José Pablo Fernández \n" +
		" Luis Alejandro Corrales \n Gabriela Delgado Quesada \n" + copyright + " 2014-2016 PreparaTEC \n Todos los derechos reservados";

		legendLabel.setTextColor(Color.parseColor("#414042"));
		legendLabel.setText(legendString);
		
		authorsLabel.setTextColor(Color.parseColor("#414042"));
		authorsLabel.setText(authorsString);
	}
}
