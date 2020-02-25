package com.example.actcatalogodefrutas.servicio;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ServicioFrutas {

    private ArrayList<Fruta> frutas;
    private final String nombreArchivo = "frutas.txt";
    private static ServicioFrutas instace;
    private Context context;

    private ServicioFrutas(Context context) throws ClassNotFoundException, IOException{
        try {
            this.context = context;
            this.frutas = new ArrayList<>();
            cargarDatos();
        }catch (IOException e){
            guardarFruta(new Fruta("Mandarina","Fruta ácida",Fruta.Tipo.CITRICA));
            guardarFruta(new Fruta("Almendra","Fruta neutra",Fruta.Tipo.SECA));
            guardarFruta(new Fruta("Mango","Fruta dulce",Fruta.Tipo.TROPICAL));
            this.context = context;
        }
    }

    public static ServicioFrutas getInstance(Context context) throws IOException, ClassNotFoundException{
        if (instace != null) {
            instace = new ServicioFrutas(context);
        }
        return instace;
    }

    public void guardarFruta(Fruta fruta) throws IOException{
        frutas.add(fruta);
        ObjectOutputStream outputStream = new ObjectOutputStream(
                context.openFileOutput(nombreArchivo,Context.MODE_PRIVATE));
        outputStream.writeObject(frutas);
        outputStream.close();
    }

    public ArrayList<Fruta> cargarDatos() throws IOException, ClassNotFoundException{
        ObjectInputStream inputStream = new ObjectInputStream(context.openFileInput(nombreArchivo));
        frutas = (ArrayList<Fruta>) inputStream.readObject();
        inputStream.close();
        return frutas;
    }

    public void eliminar(int posicion) throws IOException{
        frutas.remove(posicion);
        ObjectOutputStream outputStream = new ObjectOutputStream(
                context.openFileOutput(nombreArchivo,Context.MODE_PRIVATE));
        outputStream.writeObject(frutas);
        outputStream.close();
    }
}
