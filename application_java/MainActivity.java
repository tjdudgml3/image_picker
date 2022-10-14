package com.example.imagepicker;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.Path;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
//import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ArrayList<Uri> file_pet = new ArrayList<>();
    ArrayList<Uri> file_person = new ArrayList<>();
    ArrayList<Uri> file_kakao = new ArrayList<>();
    ArrayList<Uri> file_game = new ArrayList<>();
    ArrayList<Uri> file_food = new ArrayList<>();
    ArrayList<Uri> file_unknown = new ArrayList<>();
    RecyclerView recyclerView;  // 이미지를 보여줄 리사이클러뷰
    RecyclerView recyclerView_pet;
    RecyclerView recyclerView_person;
    RecyclerView recyclerView_kakao;
    RecyclerView recyclerView_game;
    RecyclerView recyclerView_food;
    MultiImageAdapter adapter_pet;
    MultiImageAdapter adapter_person;
    MultiImageAdapter adapter_kakao;
    MultiImageAdapter adapter_game;
    MultiImageAdapter adapter_food;
    int subactiviy_switch = 0;
    // 리사이클러뷰에 적용시킬 어댑터
    private ClassifierWithSupport cls;
    private ImageView imageView;
    private TextView textView;
    private final String TAG = this.getClass().getSimpleName();

    //MultiImageAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_main1);


        Button selectBtn = (Button) findViewById(R.id.selectBtn);
        selectBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                getImageFromGallery();


            }



        });

        cls = new ClassifierWithSupport(this);
        try {
            cls.init();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public void getImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        launcher.launch(Intent.createChooser(intent, "Add images"));
        //subactivity();

    }

    ArrayList<Uri> uriList = new ArrayList<>();

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        ClipData clipdata = data.getClipData();

                        uriList.clear();
                        if (clipdata != null) {
                            int count = clipdata.getItemCount();
                            for (int i = 0; i < count; i++) {
                                uriList.add(clipdata.getItemAt(i).getUri());

                            }

                            int uriList_size = uriList.size();
                            Bitmap bitmap = null;
                            for (int i = 0; i < uriList_size; i++) {
                                Uri image_uri = uriList.get(i);
                                try {
                                    if (Build.VERSION.SDK_INT >= 29) {

                                        ImageDecoder.Source src =
                                                ImageDecoder.createSource(getContentResolver(), image_uri);
                                        bitmap = ImageDecoder.decodeBitmap(src);
                                    } else {

                                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image_uri);
                                    }
                                }
                                catch(IOException ioe){
                                    Log.e("tag","Failed to read",ioe);
                                }
                                if(bitmap != null){
                                    Pair<String, Float> output = cls.classify(bitmap);
                                    String res = String.format(Locale.ENGLISH,
                                            "Types of picture : %s\n Accuracy : %.2f%%",
                                            output.first, output.second * 100);
                                    imageView = (ImageView) findViewById(R.id.imageView);
                                    textView = (TextView) findViewById(R.id.textView);
                                    imageView.setImageBitmap(bitmap);
                                    textView.setText(res);
                                    try{
                                        if (output.first.equals("Pet")){
                                            file_pet.add(image_uri);
                                            Log.d("tag","pet");
                                        }else if(output.first.equals("Person")){
                                            file_person.add(image_uri);
                                            Log.d("tag","person");
                                        }else if(output.first.equals("KaKao-talk")){
                                            file_kakao.add(image_uri);
                                            Log.d("tag","kakao");
                                        }else if(output.first.equals("Game")){
                                            file_game.add(image_uri);
                                            Log.d("tag","game");
                                        }else if(output.first.equals("Food")){
                                            file_food.add(image_uri);
                                            Log.d("tag","food");
                                        }else{
                                            file_unknown.add(image_uri);
                                            Log.d("tag",output.first);
                                        }
                                    }
                                    finally {

                                    }

                                    bitmap = null;
                                    image_uri = null;


                                }
                                if( i == uriList_size-1){
                                    subactiviy_switch = 1;
                                    Log.d("asd","asd");
                                }
                            }
                            if(subactiviy_switch == 1){

                                //subactivity();


                            }
                            subactiviy_switch = 0;
                        }

                    }

                }
            }
    );

    public void subactivity() {
        Intent intent;
        intent = new Intent(MainActivity.this, Subactivity.class);
        intent.putParcelableArrayListExtra("pet",file_pet);
        intent.putParcelableArrayListExtra("food",file_food);
        intent.putParcelableArrayListExtra("person",file_person);
        intent.putParcelableArrayListExtra("game",file_game);
        intent.putParcelableArrayListExtra("kakao",file_kakao);
        intent.putParcelableArrayListExtra("unknown",file_unknown);
        intent.putExtra("un",file_unknown);

        startActivity(intent);


    }

}




