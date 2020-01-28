package com.logistics.clonezomato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class Orders extends AppCompatActivity {

    CheckBox v1,v2,v3,v4,nv1,nv2,nv3,nv4,d1,d2,d3,d4;
    int total = 0;
    TextView tot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        Intent i =getIntent();
        String t = i.getStringExtra("Title");
        String d = i.getStringExtra("Description");
        Bundle bundle = this.getIntent().getExtras();
        int pic = bundle.getInt("image");

        TextView t1 = (TextView) findViewById(R.id.t);
        TextView t2 = (TextView) findViewById(R.id.d);

        t1.setText(t);
        t2.setText(d);

        ImageView v = (ImageView) findViewById(R.id.i);
        v.setImageResource(pic);
        Button b1 = (Button) findViewById(R.id.caltotal);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total = 0;
                addListenerOnButton();
                tot.setText("Total : "+total);
            }
        });
    }

    public void addListenerOnButton(){
        v1 = (CheckBox) findViewById(R.id.pakoda);
        v2 = (CheckBox) findViewById(R.id.alooChat);
        v3 = (CheckBox) findViewById(R.id.paneerchilly);
        v4 = (CheckBox) findViewById(R.id.cheeseroll);

        nv1 = (CheckBox) findViewById(R.id.chibiryani);
        nv2 = (CheckBox) findViewById(R.id.chiafghani);
        nv3 = (CheckBox) findViewById(R.id.chichilly);
        nv4 = (CheckBox) findViewById(R.id.chiHandi);

        d1 = (CheckBox) findViewById(R.id.colddrinks);
        d2 = (CheckBox) findViewById(R.id.tea);
        d3 = (CheckBox) findViewById(R.id.coffee);
        d4 = (CheckBox) findViewById(R.id.lassi);


        tot = (TextView) findViewById(R.id.total);

        if(v1.isChecked()){
            total += 70;
        }
        if(v2.isChecked()){
            total += 50;
        }

        if(v3.isChecked()){
            total += 140;
        }

        if(v4.isChecked()){
            total += 120;
        }
        if(nv1.isChecked()){
            total += 70;
        }
        if(nv2.isChecked()){
            total += 50;
        }

        if(nv3.isChecked()){
            total += 140;
        }

        if(nv4.isChecked()){
            total += 120;
        }

        if(d1.isChecked()){
            total += 20;
        }
        if(d2.isChecked()){
            total += 10;
        }

        if(d3.isChecked()){
            total += 20;
        }

        if(d4.isChecked()){
            total += 35;
        }
    }
}
