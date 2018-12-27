package com.example.chethan.retrofitexample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chethan.retrofitexample.RetrofitPackage.ClassInstance;
import com.example.chethan.retrofitexample.RetrofitPackage.InterfaceClass;
import com.example.chethan.retrofitexample.RetrofitPackage.ModelClass;
import com.example.chethan.retrofitexample.RetrofitPackage.PostUser;
import com.example.chethan.retrofitexample.RetrofitPackage.UserClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {

    Button getBtn,postBtn,jsonObj,arrayObj,postOneObj,postManyObj;
    LinearLayout mainLayout,GetLayout,PostLayout,RecyclerLayout;

    TextView txt1,txt2,txt3,txt4;

    RecyclerView recyclerView;
    Adapter adapter;

    InterfaceClass interfaceClass;

    List<ModelClass.ArrayValue> list = new ArrayList<>();

    List<UserClass.arraydata> list1 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getBtn = findViewById(R.id.get_id);
        postBtn = findViewById(R.id.post_id);
        jsonObj = findViewById(R.id.json_obj_id);
        arrayObj = findViewById(R.id.arry_obj_id);
        postOneObj = findViewById(R.id.post_one_id);
        postManyObj = findViewById(R.id.post_multi_id);

        mainLayout = findViewById(R.id.mainLay_id);
        GetLayout = findViewById(R.id.getLay);
        PostLayout = findViewById(R.id.postLay);
        RecyclerLayout = findViewById(R.id.recyclerLay);

        txt1 = findViewById(R.id.txt1_id);
        txt2 = findViewById(R.id.txt2_id);
        txt3 = findViewById(R.id.txt3_id);
        txt4 = findViewById(R.id.txt4_id);


        if (mainLayout.getVisibility() == View.VISIBLE){
            GetLayout.setVisibility(View.GONE);
            PostLayout.setVisibility(View.GONE);
            RecyclerLayout.setVisibility(View.GONE);

        }else if (GetLayout.getVisibility() == View.VISIBLE){
            mainLayout.setVisibility(View.GONE);
            PostLayout.setVisibility(View.GONE);
            RecyclerLayout.setVisibility(View.GONE);

        }else if (PostLayout.getVisibility()==View.VISIBLE){
            mainLayout.setVisibility(View.GONE);
            GetLayout.setVisibility(View.GONE);
            RecyclerLayout.setVisibility(View.GONE);
        }else if (RecyclerLayout.getVisibility()==View.VISIBLE){
            mainLayout.setVisibility(View.GONE);
            PostLayout.setVisibility(View.GONE);
            GetLayout.setVisibility(View.GONE);
        }


        recyclerView = findViewById(R.id.recycler_id);
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);



        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                mainLayout.setVisibility(View.GONE);

                Toast.makeText(MainActivity.this,"Get",Toast.LENGTH_SHORT).show();

                GetLayout.setVisibility(View.VISIBLE);
                mainLayout.setVisibility(View.GONE);
                PostLayout.setVisibility(View.GONE);
                RecyclerLayout.setVisibility(View.GONE);







            }
        });

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,"Post",Toast.LENGTH_SHORT).show();

                GetLayout.setVisibility(View.GONE);
                mainLayout.setVisibility(View.GONE);
                PostLayout.setVisibility(View.VISIBLE);
                RecyclerLayout.setVisibility(View.GONE);


            }
        });

        jsonObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,"JSON Get Data",Toast.LENGTH_SHORT).show();

                GetLayout.setVisibility(View.GONE);
                mainLayout.setVisibility(View.GONE);
                PostLayout.setVisibility(View.GONE);
                RecyclerLayout.setVisibility(View.VISIBLE);



                interfaceClass = ClassInstance.getRetrofit().create(InterfaceClass.class);

                Call<ModelClass>call = interfaceClass.getModel();

                call.enqueue(new Callback<ModelClass>() {
                    @Override
                    public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {

                        ModelClass modelClass = response.body();

                        System.out.println("modelclass:"+modelClass);

                        System.out.println("Page:"+modelClass.Page);

                        txt1.setText("Page:\t"+modelClass.Page);
                        txt2.setText("Per_Page:\t"+modelClass.Per_page);
                        txt3.setText("Total:\t"+modelClass.Total);
                        txt4.setText("Total_pages:\t"+modelClass.Total_pages);


                        list = modelClass.data;


                        adapter.notifyDataSetChanged();



                    }

                    @Override
                    public void onFailure(Call<ModelClass> call, Throwable t) {

                    }
                });




            }
        });

        arrayObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,"Array Data To Fetch",Toast.LENGTH_SHORT).show();

                GetLayout.setVisibility(View.GONE);
                mainLayout.setVisibility(View.GONE);
                PostLayout.setVisibility(View.GONE);
                RecyclerLayout.setVisibility(View.VISIBLE);


                interfaceClass = ClassInstance.getRetrofit().create(InterfaceClass.class);

                Call<UserClass> userClassCall = interfaceClass.USER_CLASS_CALL("2");

                userClassCall.enqueue(new Callback<UserClass>() {
                    @Override
                    public void onResponse(Call<UserClass> call, Response<UserClass> response) {

                        UserClass userClass = response.body();

                        txt1.setText("Page:\t"+userClass.Page);
                        txt2.setText("Per_Page:\t"+userClass.Per_page);
                        txt3.setText("Total:\t"+userClass.Total);
                        txt4.setText("Total_pages:\t"+userClass.Total_pages);

                         list1 = userClass.Data;

                        for (UserClass.arraydata arraydata:list1){

                            System.out.println("Name:"+arraydata.First_name+arraydata.Last_name+"id:"+arraydata.Id+"avatar:"+arraydata.Avatar);
                        }

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<UserClass> call, Throwable t) {

                    }
                });





            }
        });

        postOneObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetLayout.setVisibility(View.GONE);
                mainLayout.setVisibility(View.GONE);
                PostLayout.setVisibility(View.GONE);
                RecyclerLayout.setVisibility(View.VISIBLE);

                recyclerView.setVisibility(View.GONE);

//                Toast.makeText(MainActivity.this,"Post One Object",Toast.LENGTH_SHORT).show();


                interfaceClass = ClassInstance.getRetrofit().create(InterfaceClass.class);

                PostUser postUser = new PostUser("Chethan","Software Engineer");

                Call<PostUser>postUserCall = interfaceClass.POST_USER_CALL(postUser);

                postUserCall.enqueue(new Callback<PostUser>() {
                    @Override
                    public void onResponse(Call<PostUser> call, Response<PostUser> response) {
                        PostUser postUser1 = response.body();

                        txt1.setText(postUser1.toString());



                    }

                    @Override
                    public void onFailure(Call<PostUser> call, Throwable t) {

                    }
                });

            }
        });

        postManyObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                GetLayout.setVisibility(View.GONE);
                mainLayout.setVisibility(View.GONE);
                PostLayout.setVisibility(View.GONE);
                RecyclerLayout.setVisibility(View.VISIBLE);

                recyclerView.setVisibility(View.VISIBLE);

//                Toast.makeText(MainActivity.this,"Post Many Object",Toast.LENGTH_SHORT).show();

                interfaceClass =ClassInstance.getRetrofit().create(InterfaceClass.class);

                Call<UserClass>classCall = interfaceClass.CALL_LIST("ChethanM","Android Developer");

                classCall.enqueue(new Callback<UserClass>() {
                    @Override
                    public void onResponse(Call<UserClass> call, Response<UserClass> response) {

                        UserClass aClass = response.body();


                        txt1.setText("Page:\t"+aClass.Page);
                        txt2.setText("Per_Page:\t"+aClass.Per_page);
                        txt3.setText("Toatl:\t"+aClass.Total);
                        txt4.setText("Total_Pages:\t"+aClass.Total_pages);


                        System.out.println("totalPage:"+aClass.Total_pages);

                        list1 = aClass.Data;

                        System.out.println("List1Size:"+list1.size());

                        for (UserClass.arraydata arraydata:list1){

                            System.out.println("Name:"+arraydata.First_name+arraydata.Last_name+"Id:"+arraydata.Id+"Avatar:"+arraydata.Avatar);
                        }

                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<UserClass> call, Throwable t) {

                    }
                });


            }
        });


    }



    public class Adapter extends RecyclerView.Adapter<Adapter.Holder>{

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.recyclerlayout,viewGroup,false);
            Holder holder = new Holder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder,  int i) {




//            holder.id.setText(list.get(i).Id);
//            holder.name.setText(list.get(i).Name);
//            holder.value.setText(list.get(i).Pantone_value);
//            holder.year.setText(list.get(i).Year);


            holder.id.setText(list1.get(i).Id);
            holder.name.setText(list1.get(i).First_name);
            holder.year.setText(list1.get(i).Last_name);
            holder.value.setText(list1.get(i).Avatar);



        }

        @Override
        public int getItemCount() {
//            return list.size();

            return list1.size();
        }


        public class Holder extends RecyclerView.ViewHolder{

            private TextView id,name,year,value;


            public Holder(@NonNull View itemView) {
                super(itemView);

                id = itemView.findViewById(R.id.r1_id);
                name = itemView.findViewById(R.id.r2_id);
                year = itemView.findViewById(R.id.r3_id);
                value = itemView.findViewById(R.id.r4_id);
            }
        }
    }




    @Override
    public void onBackPressed() {
//        super.onBackPressed();

//        if (mainLayout.getVisibility() != View.VISIBLE){
//
//            mainLayout.setVisibility(View.VISIBLE);
//            GetLayout.setVisibility(View.GONE);
//            PostLayout.setVisibility(View.GONE);
//            RecyclerLayout.setVisibility(View.GONE);
//
//            System.out.println("One:1");
//
//        }else if (GetLayout.getVisibility() != View.VISIBLE){
//
//            GetLayout.setVisibility(View.VISIBLE);
//            mainLayout.setVisibility(View.GONE);
//            PostLayout.setVisibility(View.GONE);
//            RecyclerLayout.setVisibility(View.GONE);
//
//            System.out.println("Two:2");
//
//        }else if (PostLayout.getVisibility() !=View.VISIBLE){
//
//            PostLayout.setVisibility(View.VISIBLE );
//            mainLayout.setVisibility(View.GONE);
//            GetLayout.setVisibility(View.GONE);
//            RecyclerLayout.setVisibility(View.GONE);
//
//            System.out.println("three:3");
//
//        }else if (RecyclerLayout.getVisibility() !=View.VISIBLE){
//
//            RecyclerLayout.setVisibility(View.VISIBLE);
//            mainLayout.setVisibility(View.GONE);
//            PostLayout.setVisibility(View.GONE);
//            GetLayout.setVisibility(View.GONE);
//
//            System.out.println("four:4");
//
//        }else {
//
//            super.onBackPressed();
//
//        }


        if (mainLayout.getVisibility() !=View.VISIBLE ){
            mainLayout.setVisibility(View.VISIBLE);
            GetLayout.setVisibility(View.GONE);
            PostLayout.setVisibility(View.GONE);
            RecyclerLayout.setVisibility(View.GONE);
        }else {
            super.onBackPressed();
        }







    }
}
