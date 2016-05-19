package com.app.preparatec2;

import com.app.preparatec2.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Menu extends Activity {

	Button botonAcerca, botonPrepara, botonReta;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		botonAcerca=(Button)findViewById(R.id.btnAcerca);
		botonPrepara=(Button)findViewById(R.id.btnPrepara);
		botonReta=(Button)findViewById(R.id.btnReta);

		botonAcerca.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Menu.this, About.class);
				startActivity(intent);
				//finish();
			} 
		});

		botonPrepara.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Menu.this, Normal.class);
				startActivity(intent);
				//finish();

			} 
		});

		botonReta.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Menu.this, Retate.class);
				startActivity(intent);
				//finish();
			} 
		});

	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
}