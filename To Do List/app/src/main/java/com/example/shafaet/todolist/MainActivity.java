package com.example.shafaet.todolist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.shafaet.todolist.Adapter.ListItemAdapter;
import com.example.shafaet.todolist.Model.ToDo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dmax.dialog.SpotsDialog;


public class MainActivity extends AppCompatActivity {

    List<ToDo> toDoList = new ArrayList<>();
    FirebaseFirestore db;
    RecyclerView listItem;
    RecyclerView.LayoutManager layoutManager;
    FloatingActionButton fabAdd, fabUpdate;
    public MaterialEditText editTextTitle, editTextDescription;
    ListItemAdapter adapter;
    public String idUpdate = ""; //id of item need to update
    SpotsDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init Firestore
        db = FirebaseFirestore.getInstance();

        //view
        dialog = new SpotsDialog(this);
        editTextTitle = findViewById(R.id.id_et_title);
        editTextDescription = findViewById(R.id.id_et_description);
        fabAdd = findViewById(R.id.id_fab_add);
        fabUpdate = findViewById(R.id.id_fab_update);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextTitle.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Need Title", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(editTextDescription.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Need Description", Toast.LENGTH_SHORT).show();
                } else {
                    //Add new
                    setData(editTextTitle.getText().toString(), editTextDescription.getText().toString());
                    editTextTitle.setText("");
                    editTextDescription.setText("");
                }


            }
        });
        fabUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!TextUtils.isEmpty(editTextTitle.getText().toString()) || (!TextUtils.isEmpty(editTextDescription.getText().toString())))) {
                    upDateData(editTextTitle.getText().toString(), editTextDescription.getText().toString());
                } else {
                    Toast.makeText(MainActivity.this, "Please Write Something!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listItem = findViewById(R.id.id_list_to_do);
        listItem.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        listItem.setLayoutManager(layoutManager);

        loadData(); //load data from Firestore

    }

    private void upDateData(String title, String description) {
        db.collection("ToDoList").document(idUpdate)
                .update("editTextTitle", title, "editTextDescription", description)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Update Successful!", Toast.LENGTH_SHORT).show();
                    }
                });
        //Realtime update refresh data
        db.collection("ToDoList").document(idUpdate)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                        loadData();
                    }
                });
    }

    private void setData(String title, String description) {
        //Random id
        String id = UUID.randomUUID().toString();
        Map<String, Object> toDo = new HashMap<>();
        toDo.put("id", id);
        toDo.put("editTextTitle", title);
        toDo.put("editTextDescription", description);

        db.collection("ToDoList").document(id)
                .set(toDo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "Add Successful!", Toast.LENGTH_SHORT).show();
                //Refresh data
                loadData();
            }
        });
    }

    private void loadData() {
        dialog.show();
        if (toDoList.size() > 0) {
            toDoList.clear();//remove old value
        }
        db.collection("ToDoList")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot doc : task.getResult()) {
                            ToDo toDo = new ToDo(doc.getString("id"),
                                    doc.getString("editTextTitle"),
                                    doc.getString("editTextDescription"));
                            toDoList.add(toDo);
                        }
                        adapter = new ListItemAdapter(MainActivity.this, toDoList);
                        listItem.setAdapter(adapter);
                        dialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals("DELETE")) {
            deleteItem(item.getOrder());
        }
        return super.onContextItemSelected(item);
    }

    private void deleteItem(int index) {
        db.collection("ToDoList")
                .document(toDoList.get(index).getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        loadData();
                    }
                });
    }

}
