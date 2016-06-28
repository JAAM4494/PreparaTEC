package gotouch.app.preparatec;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import gotouch.app.preparatec.R;

/**
 * Clase encargada de la pantalla principal.
 */
public class Home_Screen extends Activity {

	private Button aboutBtn, trainingBtn, dareBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		aboutBtn 	= (Button)findViewById(R.id.btnAcerca);
		trainingBtn = (Button)findViewById(R.id.btnPrepara);
		dareBtn 	= (Button)findViewById(R.id.btnReta);

		aboutBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Home_Screen.this, About_Screen.class);
				startActivity(intent);
			}
		});

		trainingBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Home_Screen.this, Training_Screen.class);
				startActivity(intent);

			}
		});

		dareBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Home_Screen.this, Dare_Screen.class);
				startActivity(intent);
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