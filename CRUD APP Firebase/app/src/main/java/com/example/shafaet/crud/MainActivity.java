package com.example.shafaet.crud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private Spinner spinner;
    private Button buttonAddArtist;
    private ListView listView;
    List<Artist> artistList;
    DatabaseReference databaseReferenceArtist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get firebase database reference. here artist node
        databaseReferenceArtist = FirebaseDatabase.getInstance().getReference("artist");
        editTextName = findViewById(R.id.id_et_name);
        spinner = findViewById(R.id.id_spinner);
        buttonAddArtist = findViewById(R.id.id_btn_add_artist);
        listView = findViewById(R.id.id_lv_list_view);

        artistList = new ArrayList<Artist>();
        buttonAddArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArtist();
            }
        });
    }

    private void addArtist() {
        String artistName = editTextName.getText().toString().trim();
        String genre = spinner.getSelectedItem().toString();

        if(!TextUtils.isEmpty(artistName)){
            //push method will create a unique string inside artist node. get key method will
            //return the unique string created by push method.
            String id = databaseReferenceArtist.push().getKey();
            //Now creating Artist class object and passing id, artistName and genre by constructor.
            Artist artist = new Artist(id, artistName, genre);
            //Sending artist object to Firebase Database inside id, artist will save
            databaseReferenceArtist.child(id).setValue(artist);
            Toast.makeText(this, "Artist added", Toast.LENGTH_SHORT).show();
            //Every time when addArtist method is called, push method will generate uniquely
            //identified key string and by get key method we will store it to id. when sending
            //artist object to firebase database inside each id artist object will saved.
        }
        else{
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReferenceArtist.addValueEventListener(new ValueEventListener() {
            //onDataChange method will execute everytime when data is changed firebase database.
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistList.clear();
                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Artist artist = artistSnapshot.getValue(Artist.class);

                    artistList.add(artist);
                }

                ArtistList adapter = new ArtistList(MainActivity.this, artistList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
