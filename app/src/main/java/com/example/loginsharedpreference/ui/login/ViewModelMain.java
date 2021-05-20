package com.example.loginsharedpreference.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginsharedpreference.model.Usuario;
import com.example.loginsharedpreference.request.ApiClient;
import com.example.loginsharedpreference.ui.registro.RegistroActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ViewModelMain extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> mutableUs;
    private MutableLiveData<String> msj;

    public ViewModelMain(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
    }
    public LiveData<Usuario> getUsuario(){
        if(mutableUs==null){
            mutableUs= new MutableLiveData<>();

        }
        return mutableUs;
    }
    public LiveData<String> getMsj(){
        if(msj==null){
            msj= new MutableLiveData<>();

        }
        return msj;
    }

    public void Ingresar (String mail, String pass) {
        SharedPreferences sp=context.getSharedPreferences("datos",0);
        String email=sp.getString("email", "inexistente");
        String password=sp.getString("password", "inexistente");

        if(mail.equals(email) && pass.equals(password)){
            Usuario us= ApiClient.login(context, mail, pass);
            mutableUs.setValue(us);
        }
        else msj.setValue("Usuario y/o clave incorrectos");

    }

    /*public void Ingresar (Context context, String mail, String password) {
        Usuario us = ApiClient.login(context, mail, password);
        mutableUs.setValue(us);
    }*/
    public void Registrarse (){
        Intent i = new Intent(context, RegistroActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);



    }

}
