package co.jh.popupsample;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class BannerViewActivity extends AppCompatActivity {

    LinearLayout layout;
    LinearLayout.LayoutParams params;
    ImageButton closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_view);

        layout = findViewById(R.id.layout);
        params = (LinearLayout.LayoutParams) layout.getLayoutParams();
        layout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        closeButton = findViewById(R.id.close_btn);

        closeButton.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    layout.animate()
                            .translationY(720)
                            .scaleY(0.2f)
                            .alpha(0.2f)
                            .setDuration(800)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    layout.setAlpha(0.0f);
                                    closePopup();
                                }
                            });
                    return true;
                }
                return false;
            }
        });
    }

    public void closePopup() {
        SharedPreferences pref = BannerViewActivity.this.getSharedPreferences("my_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("stringValue", "팝업이유");
        editor.commit();
        BannerViewActivity.this.finish();
    }

    private void change(float v) {
        params.height = (int) (v*layout.getHeight());
        layout.setLayoutParams(params);
    }
}