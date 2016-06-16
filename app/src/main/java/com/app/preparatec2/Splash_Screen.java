package com.app.preparatec2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Clase encargada de la pantalla de bienvenida.
 */
public class Splash_Screen extends Activity {
	private TextView firstTV;

	private static String TAG = Splash_Screen.class.getName();
	private static long SLEEP_TIME = 0;

	/** Called when the activity is first created. */

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Window window = getWindow();
		window.setFormat(PixelFormat.RGBA_8888);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);
		StartAnimations();
		IntentLauncher launcher = new IntentLauncher();
		launcher.start();
	}

	/**
	 * Metodo encargado de comenzar las animaciones del splash.
	 */
	private void StartAnimations() {

		Animation anim = AnimationUtils.loadAnimation(this,R.anim.alpha);
		anim.reset();
		LinearLayout l=(LinearLayout) findViewById(R.id.la_Main);
		l.clearAnimation();
		l.startAnimation(anim);

		anim = AnimationUtils.loadAnimation(this, R.anim.translate);
		anim.reset();
		//ImageView iv = (ImageView) findViewById(R.id.logo);
		//iv.clearAnimation();
		//iv.startAnimation(anim);
	}

	@Override
	public void onBackPressed() {
	  // Log.d("CDA", "onBackPressed Called");
	   Intent setIntent = new Intent(Intent.ACTION_MAIN);
	   setIntent.addCategory(Intent.CATEGORY_HOME);
	   setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   startActivity(setIntent);
	}

	/**
	 * Clase encargada de la creacion del thread del splash.
	 */
	private class IntentLauncher extends Thread {

		@Override
		public void run() {
			try {
				Thread.sleep(SLEEP_TIME * 1000);
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}

			Intent intent = new Intent(Splash_Screen.this, Home_Screen.class);
			Splash_Screen.this.startActivity(intent);
			Splash_Screen.this.finish();
		}
	}
}