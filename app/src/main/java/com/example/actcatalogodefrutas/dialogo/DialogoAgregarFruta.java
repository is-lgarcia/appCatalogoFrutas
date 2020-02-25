package com.example.actcatalogodefrutas.dialogo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.actcatalogodefrutas.R;
import com.example.actcatalogodefrutas.servicio.Fruta;

public class DialogoAgregarFruta extends DialogFragment {

    public static final String TAG = "dialogo_agregar";

    public interface OnAgregarFrutaListener {
        void onAgregarFruta(Fruta fruta);
    }

    private OnAgregarFrutaListener listener;

    private EditText editNombre, editDescripcion;
    private Spinner spinnerTiposFruta;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(getContext()).inflate(R.layout.dialogo_agregar_fruta, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Agregar Fruta");
        builder.setView(view);

        editNombre = view.findViewById(R.id.edit_nombre_fruta);
        editDescripcion = view.findViewById(R.id.edit_descripcion_fruta);
        spinnerTiposFruta = view.findViewById(R.id.spn_tipos_frutas);
        Button btnAgregar = view.findViewById(R.id.btn_agregar_fruta);
        Button btnCancelar = view.findViewById(R.id.btn_cancelar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editNombre.getText().toString().equals("") &&
                        !editDescripcion.getText().toString().equals("")) {
                    Fruta.Tipo tipo = Fruta.Tipo.TROPICAL;
                    switch (spinnerTiposFruta.getSelectedItemPosition()) {
                        case 0:
                            tipo = Fruta.Tipo.TROPICAL;
                            break;
                        case 1:
                            tipo = Fruta.Tipo.DELBOSQUE;
                            break;
                        case 2:
                            tipo = Fruta.Tipo.CITRICA;
                            break;
                        case 3:
                            tipo = Fruta.Tipo.SECA;
                            break;
                    }
                    Fruta fruta = new Fruta(editNombre.getText().toString()
                            , editDescripcion.getText().toString(), tipo);
                    listener.onAgregarFruta(fruta);
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "Por favor llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (OnAgregarFrutaListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("La actividad no implemento el listener \n" + e);
        }
    }
}
