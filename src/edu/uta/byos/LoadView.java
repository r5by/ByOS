package edu.uta.byos;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoadView extends Activity implements OnTouchListener {

	// private GameView gameView;
	private TextView touchToStart = null;
	private ImageView logo = null;
	private RelativeLayout startPage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_load_view);
		startPage = (RelativeLayout) findViewById(R.id.start_page);
		startPage.setOnTouchListener(this);

		final Animation alpha = AnimationUtils.loadAnimation(LoadView.this,
				R.anim.touch_to_start);
		final AnimationSet logoShow = (AnimationSet) AnimationUtils
				.loadAnimation(LoadView.this, R.anim.logo_show);
		logoShow.setFillAfter(true);

		/*
		 * AnimationListener settings
		 */
//		logoShow.setAnimationListener(new AnimationListener() {
//
//			@Override
//			public void onAnimationStart(Animation animation) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onAnimationRepeat(Animation animation) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onAnimationEnd(Animation animation) {
//				touchToStart = (TextView) findViewById(R.id.touch_to_start);
//				touchToStart.setVisibility(View.VISIBLE);
//				touchToStart.setAnimation(alpha);
//			}
//		});


		logo = (ImageView) findViewById(R.id.logo);
		logo.setImageResource(R.drawable.icon);
		logo.setAnimation(logoShow);

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
//		Intent intent = new Intent();
//        intent.setClass(LoadView.this, GameActivity.class);
		Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        // Close load game view
        finish();
		return true;
	}


}
