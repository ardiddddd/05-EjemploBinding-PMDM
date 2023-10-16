package sergio.moron.a05_ejemplobinding_pmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.Serializable;

import sergio.moron.a05_ejemplobinding_pmdm.databinding.ActivityAddAlumnoBinding;
import sergio.moron.a05_ejemplobinding_pmdm.modelos.Alumno;

public class AddAlumnoActivity extends AppCompatActivity {

    private ActivityAddAlumnoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_alumno);
        binding = ActivityAddAlumnoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCancelarAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnCrearAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Añadir lo que escriba el alumno
                Alumno alumno = craerAlumno();

                if (alumno == null){
                    Toast.makeText(AddAlumnoActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }

                //Enviar la info al principal junto con resultado Ok
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("ALUMNO", alumno);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);

                //Terminar
                finish();
            }
        });
    }

    private Alumno craerAlumno() {
        if (binding.txtNombreAddAlumno.getText().toString().isEmpty()){
            return null;
        }
        if (binding.txtApellidosAddAlumno.getText().toString().isEmpty()){
            return null;
        }
        if (binding.spCiclosAddAlumno.getSelectedItemPosition() == 0){
            return null;
        }
        if (!binding.rbGrupoAAddAlumno.isChecked() && !binding.rbGrupoBAddAlumno.isChecked() && !binding.rbGrupoCAddAlumno.isChecked()){
            return null;
        }

        RadioButton rb = findViewById(binding.rgGrupoAddAlumno.getCheckedRadioButtonId());
        char letra = rb.getText().charAt(rb.getText().length() - 1);

        Alumno alumno = new Alumno(
            binding.txtNombreAddAlumno.getText().toString(),
            binding.txtApellidosAddAlumno.getText().toString(),
            binding.spCiclosAddAlumno.getSelectedItem().toString(),
            letra
        );
        return alumno;
    }
}