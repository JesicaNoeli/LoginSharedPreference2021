package com.example.loginsharedpreference.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginsharedpreference.model.Usuario;
import com.example.loginsharedpreference.request.ApiClient;
import com.example.loginsharedpreference.ui.login.MainActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ViewModelRegistro extends AndroidViewModel {
    private MutableLiveData<Usuario> mutableUs;
    Context context;

    public ViewModelRegistro(@NonNull Application application) {

        super(application);
        context= application.getApplicationContext();
    }


    public LiveData<Usuario> getUsuario(){
        if(mutableUs==null){
            mutableUs= new MutableLiveData<>();

        }
        return mutableUs;
    }


    public void guardar( Usuario us ){
        ApiClient.guardar(context,us);
        Inicio();

    }

    public void mostrar(){
        Usuario us= ApiClient.leer(context);
        mutableUs.setValue(us);
    }

    public void Inicio(){
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
