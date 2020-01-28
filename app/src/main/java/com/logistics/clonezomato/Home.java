package com.logistics.clonezomato;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Home extends Activity implements TextWatcher{

    TextView zomato;
    Double latitude = 0.0;
    Double longitude = 0.0;
    static String TAG = "Home";
    Location gps_loc = null, network_loc = null, final_loc = null;
    String[] title = {"Atithi All Day Dining & Banquet","Azad restaurant","Banjara Mumbai","Bansuri","Barbequeue Nation",
            "Bhau Vada Pav","B Mad The Restaurant","Burger Spot","Earth Plate","Kampa Chinese Restaurant",
            "Krishna Restaurant & Bar","Lake View Cafe","McDonalds","Momo Cafe","The Mumbai Hub",
            "Nawab Saheb","OC The Oriental Cuisine","Peshwa Pavilliaon","Shree Sagar Restaurant","Sitara Restaurant & Bar"};

    String[] description = {"Mulund West","Mulund West","Andheri East","Mulund East","Thane West",
            "Ghatkopar West","Mulund West","Mulund West","Vile Parle East","Mulund East",
            "Mulund West","Powai","Thane West","Andheri East","Mulund West",
            "Powai","Mulund West","Andheri East","Lower Parel West","Andheri East"};

    int[] image = {R.drawable.atithi,R.drawable.azad,R.drawable.banjaramumbai,R.drawable.bansuri,R.drawable.barbequenation,
            R.drawable.bhauvadapav,R.drawable.bmad,R.drawable.burgerspot,R.drawable.earthplate,R.drawable.kampa,
            R.drawable.krishna,R.drawable.lakeview,R.drawable.mcdonalds,R.drawable.momocafe,R.drawable.mumbaihub,
            R.drawable.nawabsaheb,R.drawable.orientalcuisine,R.drawable.peshwapavillion,R.drawable.shreesagar,R.drawable.sitara};

    EditText ed1;
    ListView l1;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        zomato = (TextView) findViewById(R.id.tv1);
        zomato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, WebZom.class));
            }
        });

        ed1 = (EditText) findViewById(R.id.inputSearch);
        l1 = (ListView) findViewById(R.id.list_view);
        final ArrayList<SingleRow> myList = new ArrayList<>();

        for (int i=0;i<title.length;i++){
            SingleRow singleRow = new SingleRow(title[i],description[i],image[i]);
            myList.add(singleRow);
        }

        myAdapter = new MyAdapter(this,myList);
        l1.setAdapter(myAdapter);
        ed1.addTextChangedListener(this);

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                SingleRow item = (SingleRow) adapterView.getItemAtPosition(position);
                String title = item.getTitle();
                String desc = item.getDesc();
                int image = item.getImage();
                Intent i = new Intent(Home.this,Orders.class);
                i.putExtra("Title",title);
                i.putExtra("Description",desc);
                Bundle bundle=new Bundle();
                bundle.putInt("image",image);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }


    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        this.myAdapter.getFilter().filter(charSequence);
    }

    @Override
    public void afterTextChanged(Editable editable) {}
}
