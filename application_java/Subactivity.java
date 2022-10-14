package com.example.imagepicker;
//import com.example.imagepicker.MainActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class Subactivity extends AppCompatActivity {
    private ArrayList<Uri> pet = null;
    private ArrayList<Uri> person = null;
    private ArrayList<Uri> kakao= null;
    private ArrayList<Uri> game= null;
    private ArrayList<Uri> food= null;
    private ArrayList<Uri> unknown= null;
    int subactiviy_switch = 0;
    MultiImageAdapter adapter_pet;
    MultiImageAdapter adapter_person;
    MultiImageAdapter adapter_kakao;
    MultiImageAdapter adapter_game;
    MultiImageAdapter adapter_food;
    MultiImageAdapter adapter_unknown;
    RecyclerView recyclerView_pet;
    RecyclerView recyclerView_person;
    RecyclerView recyclerView_kakao;
    RecyclerView recyclerView_game;
    RecyclerView recyclerView_food;
    private ClassifierWithSupport cls;
    /*Subactivity(ArrayList<Uri> Pet,ArrayList<Uri> Person,ArrayList<Uri> Kakao,ArrayList<Uri>Game,ArrayList<Uri>Food,ArrayList<Uri> Unknown){
        pet = Pet;
        person = Person;
        kakao = Kakao;
        game = Game;
        food = Food;
        unknown = Unknown;

    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_view);
        Intent intent = getIntent();
        pet = intent.getParcelableArrayListExtra("pet");
        person = intent.getParcelableArrayListExtra("person");
        kakao = intent.getParcelableArrayListExtra("kakao");
        game = intent.getParcelableArrayListExtra("game");
        food = intent.getParcelableArrayListExtra("food");
        unknown = intent.getParcelableArrayListExtra("unkown");
        recyclerView_pet = findViewById(R.id.view_pet);
        recyclerView_pet.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));
        adapter_pet = new MultiImageAdapter(pet,getApplicationContext());
        recyclerView_pet.setAdapter(adapter_pet);
        Log.d("asdsad","pet");
        recyclerView_person = findViewById(R.id.view_person);
        recyclerView_person.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));
        adapter_person = new MultiImageAdapter(person,getApplicationContext());
        recyclerView_person.setAdapter(adapter_person);
        recyclerView_kakao = findViewById(R.id.view_kakao);
        recyclerView_kakao.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));
        adapter_kakao = new MultiImageAdapter(kakao,getApplicationContext());
        recyclerView_kakao.setAdapter(adapter_kakao);
        recyclerView_game= findViewById(R.id.view_game);
        recyclerView_game.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));
        adapter_game = new MultiImageAdapter(game,getApplicationContext());
        recyclerView_game.setAdapter(adapter_game);
        recyclerView_food = findViewById(R.id.view_food);
        recyclerView_food.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));
        adapter_food = new MultiImageAdapter(food,getApplicationContext());
        recyclerView_food.setAdapter(adapter_food);
        //adapter_food.clear();
        Log.d("item","pet"+adapter_pet.getItemCount());
        Log.d("item","person"+adapter_person.getItemCount());
        Log.d("item","game"+adapter_game.getItemCount());
        //adapter_pet.clear();
        //adapter_game.clear();
        //adapter_kakao.clear();
        //adapter_person.clear();
        Button selected_button = (Button) findViewById(R.id.bt1);

        selected_button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {



                onBackPressed();
                //adapter_unknown = new MultiImageAdapter(unknown,getApplicationContext());
                //recyclerView_food.setAdapter(adapter_food);


            }
        } );
//        cls = new ClassifierWithSupport(this);
//        try {
//            cls.init();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//        recyclerView_pet = findViewById(R.id.view_pet);
//        recyclerView_pet.setLayoutManager(new LinearLayoutManager(this));
//        adapter_pet = new MultiImageAdapter(pet,getApplicationContext());
//        recyclerView_pet.setAdapter(adapter_pet);
//        Log.d("asdsad","pet");
//        recyclerView_person = findViewById(R.id.view_person);
//        recyclerView_person.setLayoutManager(new LinearLayoutManager(this));
//        adapter_person = new MultiImageAdapter(person,getApplicationContext());
//        recyclerView_person.setAdapter(adapter_person);
//        recyclerView_kakao = findViewById(R.id.view_kakao);
//        recyclerView_kakao.setLayoutManager(new LinearLayoutManager(this));
//        adapter_kakao = new MultiImageAdapter(kakao,getApplicationContext());
//        recyclerView_kakao.setAdapter(adapter_kakao);
//        recyclerView_game= findViewById(R.id.view_game);
//        recyclerView_game.setLayoutManager(new LinearLayoutManager(this));
//        adapter_game = new MultiImageAdapter(game,getApplicationContext());
//        recyclerView_game.setAdapter(adapter_game);
//        recyclerView_food = findViewById(R.id.view_food);
//        recyclerView_food.setLayoutManager(new LinearLayoutManager(this));
//        adapter_food = new MultiImageAdapter(food,getApplicationContext());
//        recyclerView_food.setAdapter(adapter_food);

    }
//    public void getImageFromGallery() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        launcher.launch(Intent.createChooser(intent, "Add images"));
//        //subactivity();
//
//    }
//
//    ArrayList<Uri> uriList = new ArrayList<>();
//
//    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == RESULT_OK) {
//                        Intent data = result.getData();
//                        ClipData clipdata = data.getClipData();
//                        if (clipdata != null) {
//                            int count = clipdata.getItemCount();
//                            for (int i = 0; i < count; i++) {
//                                uriList.add(clipdata.getItemAt(i).getUri());
//
//                            }
//                            //imageView.setImageURI(uriList.get(0));
//                            int uriList_size = uriList.size();
//                            Bitmap bitmap = null;
//                            for (int i = 0; i < uriList_size; i++) {
//                                Uri image_uri = uriList.get(i);
//                                try {
//                                    if (Build.VERSION.SDK_INT >= 29) {
//
//                                        ImageDecoder.Source src =
//                                                ImageDecoder.createSource(getContentResolver(), image_uri);
//                                        bitmap = ImageDecoder.decodeBitmap(src);
//                                    } else {
//
//                                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image_uri);
//                                    }
//                                } catch (IOException ioe) {
//                                    Log.e("tag", "Failed to read", ioe);
//                                }
//                                if (bitmap != null) {
//                                    Pair<String, Float> output = cls.classify(bitmap);
//                                    String res = String.format(Locale.ENGLISH,
//                                            "Types of picture : %s\n Accuracy : %.2f%%",
//                                            output.first, output.second * 100);
//                                    //imageView.setImageBitmap(bitmap);
//                                    //textView.setText(res);
//                                    try {
//                                        if (output.first.equals("Pet")) {
//                                            pet.add(image_uri);
//                                            Log.d("tag", "pet");
//                                        } else if (output.first.equals("Person")) {
//                                            person.add(image_uri);
//                                            Log.d("tag", "person");
//                                        } else if (output.first.equals("KaKao-talk")) {
//                                            kakao.add(image_uri);
//                                            Log.d("tag", "kakao");
//                                        } else if (output.first.equals("Game")) {
//                                            game.add(image_uri);
//                                            Log.d("tag", "game");
//                                        } else if (output.first.equals("Food")) {
//                                            food.add(image_uri);
//                                            Log.d("tag", "food");
//                                        } else {
//                                            unknown.add(image_uri);
//                                            Log.d("tag", output.first);
//                                        }
//                                    } finally {
//
//                                    }
//
//                                    bitmap = null;
//                                    image_uri = null;
//
//
//                                }
////                                if (i == uriList_size - 1) {
////                                    subactiviy_switch = 1;
////                                    Log.d("asd", "asd");
////                                }
//                            }
////                            if (subactiviy_switch == 1) {
////                                subactivity();
////                            }
////                            subactiviy_switch = 0;
//                        }
//
//                    }
//
//                }
//            }
//        );
////    public void subactivity() {
////        Intent intent;
////        intent = new Intent(Subactivity.this, Subactivity.class);
////        intent.putParcelableArrayListExtra("pet",pet);
////        intent.putParcelableArrayListExtra("food",food);
////        intent.putParcelableArrayListExtra("person",person);
////        intent.putParcelableArrayListExtra("game",game);
////        intent.putParcelableArrayListExtra("kakao",kakao);
////        intent.putParcelableArrayListExtra("unknown",unknown);
////        intent.putExtra("un",unknown);
////        startActivity(intent);
////        //launcher2.launch(intent);
////    }
}