package com.example.fly_arystan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fly_arystan.Api.RegistrationApi;
import com.example.fly_arystan.Api.TicketApi;
import com.example.fly_arystan.Model.Ticket;
import com.example.fly_arystan.Model.UserRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {
    private RegistrationApi registrationApi;
    private Button register;
    private EditText nam;
    private EditText surnam;
    private EditText birt;
    private EditText national;
    private EditText passpor;
    private EditText expire_dat;
    private EditText mai;
    private EditText mobil;
    private EditText passwor1;
    private EditText passwor2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        nam = view.findViewById(R.id.name);
        surnam = view.findViewById(R.id.surname);
        birt = view.findViewById(R.id.birth);
        national = view.findViewById(R.id.national_id);
        passpor = view.findViewById(R.id.passport);
        expire_dat = view.findViewById(R.id.expire_date);
        mai = view.findViewById(R.id.mail);
        mobil = view.findViewById(R.id.mobile);
        passwor1 = view.findViewById(R.id.password1);
        passwor2 = view.findViewById(R.id.password2);


        register = view.findViewById(R.id.register);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
//                .addInterceptor(new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request originalRequest = chain.request();
//
//                Request newRequest = originalRequest.newBuilder()
//                        .header("Interceptor-Header", "xyz")
//                        .build();
//
//                return chain.proceed(newRequest);
//            }
//        })
//                .addInterceptor(loggingInterceptor)
//                .build();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:6003/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        registrationApi = retrofit.create(RegistrationApi.class);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPost(nam.getText().toString(),surnam.getText().toString(),birt.getText().toString(),national.getText().toString(),passpor.getText().toString(),expire_dat.getText().toString(),mai.getText().toString(),mobil.getText().toString(),passwor1.getText().toString(),passwor2.getText().toString());
            }
        });
        return  view;

    }
    private void createPost(String name,String surname,String birth,String national_id,String passport,String expire_date,String mail,String mobile,String password1,String password2) {
        System.out.println("???????? " +name);
        Map<String, String> fields = new HashMap<>();
        fields.put("name", name);
        fields.put("surname", surname);
        fields.put("birth", birth);
        fields.put("national_id", national_id);
        fields.put("passport", passport);
        fields.put("expire_date", expire_date);
        fields.put("mail", mail);
        fields.put("mobile", mobile);
        fields.put("password1", password1);
        fields.put("password2", password2);

        UserRegister userRegister = new UserRegister(name,surname,birth,national_id,passport,expire_date,mail,mobile,password1,password2);


        Call<UserRegister> call = registrationApi.createRegistration(userRegister);

        call.enqueue(new Callback<UserRegister>() {
            @Override
            public void onResponse(Call<UserRegister> call, Response<UserRegister> response) {

                if (!response.isSuccessful()) {
                    nam.setText("Code: " + response.code());
                    return;
                }

//                Ticket postResponse = response.body();
//                String code = "";
//                code += "Code: " + response.code() + "\n";
//                price.setText(code);
//
//                String pricetext = "";
//                pricetext += "price: " + postResponse.getPrice() + "\n";
//                price.setText(pricetext);
//
//                String timego = "";
//                timego += "Time GO: " + postResponse.getTimeGo() + "\n";
//                timeGO.setText(timego);
//
//
//                String timeout = "";
//                timeout += "Time Out: " + postResponse.getTimeOut() + "\n";
//                timeOut.setText(timeout);
            }
            @Override
            public void onFailure(Call<UserRegister> call, Throwable t) {
                //textViewResult.setText(t.getMessage());
                System.out.println("?????????????!?!?!? " + "Error" );
            }
        });
    }
}
