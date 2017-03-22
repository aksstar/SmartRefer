package com.example.aakash.smartrefer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class manure_home extends AppCompatActivity {
    private int[] images = {R.drawable.compost10,
            R.drawable.compost11,R.drawable.compost12,
            R.drawable.compost13,R.drawable.compost14,
            R.drawable.compost15,R.drawable.compost16,
            R.drawable.compost17,R.drawable.compost18
    };
    ViewFlipper viewFlipper;
    Button next,previous;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manure_home);
        viewFlipper = (ViewFlipper) findViewById(R.id.Mflipper);
        next=(Button)findViewById(R.id.Mbuttonnxt);
        previous=(Button)findViewById(R.id.Mbuttonprv);
        for(int i=0;i<images.length;i++)
        {
            //  This will create dynamic image view and add them to ViewFlipper
            setFlipperImage(images[i]);
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showNext();
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showPrevious();
            }
        });
    }
    private void setFlipperImage(int res) {
        Log.i("Set Filpper Called", res+"");
        ImageView image = new ImageView(getApplicationContext());
        image.setBackgroundResource(res);
        viewFlipper.addView(image);
    }
}
