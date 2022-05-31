package com.example.fragments2

import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Estudiante.newInstance] factory method to
 * create an instance of this fragment.
 */
class Estudiante : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //Declarar una variable cadena para almacenar la ruta relativa de la carpeta
    //dónde guardaremos el archivo. en este caso la carpeta será la de Descargas
    private lateinit var ruta : String
    //Declarar una variable cadena para el nombre del archivo
    private lateinit var nombreArchivo:String
    //Declarar la variable de referencia de tipo archivo
    private lateinit var archivo: File

    //Declaramos los objetos para los views
    private lateinit var nc : EditText
    private lateinit var nombre : EditText
    private  lateinit var carrera : Spinner
    private lateinit var semestre : Spinner
    private lateinit var grupoA : RadioButton
    private lateinit var grupoB : RadioButton
    //Declarar la variable de tipo EditText Multilínea. En este componente listo los registros
    private lateinit var edt : EditText

    private  lateinit var botonRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_estudiante, container, false)

        //Instanciamos los vies - Hacemos referencia
        nc= view.findViewById(R.id.txtNControl)
        nombre = view.findViewById(R.id.txtNombre)
        carrera= view.findViewById(R.id.cbxCarreras)
        semestre= view.findViewById((R.id.cbxSemestre))
        grupoA = view.findViewById(R.id.rbt_salaA)
        grupoB = view.findViewById(R.id.rbtGrupoB)
        edt = view.findViewById(R.id.txtRegistrosA)
        botonRegistrar = view.findViewById(R.id.btnRegistrar)

        //Inicializamos y creamos las variables
        ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .toString()
        //Asignar en una variable cadena el nombre del archivo
        nombreArchivo = "estudiantes.txt"
        //Asignar a la variable de referencia archivo la ruta y nombre de archivo. Es decir
        // apuntar hacia la ruta relativa donde se creará el archivo.
        archivo = File(ruta + "/" + nombreArchivo)
        //Apuntar hacia el componente txtRegistros que es el EditText en la interfaz


        //Verificar si existen registros y si los hay Mostrarlos en una vista multilínea
        if (archivo.exists()){
            var cadena: String = ""
            cadena = archivo.readText()
            edt.setText(cadena)
        }

        botonRegistrar.setOnClickListener(){
            fnRegistrar()
        }
        return view
    }

    fun fnRegistrar(){
        var cadena = nc.text.toString()+","+
                nombre.text.toString()+","+
                carrera.selectedItem+","+
                semestre.selectedItem+","

        cadena+= if (grupoA.isChecked) "A" else "B"
        cadena+="\n"
        //Manejo de archivos
        //La primera vez Si no existe el archivo lo creamos
        if(!archivo.exists()){
            archivo.createNewFile()
            println(archivo.path)
        }
        //Escribir datos en el archivo
        //Abrir el archivo
        // Creamos la variable que haga referencia al archivo
        val fileWrite : FileWriter = FileWriter(archivo,true)
        //Escribir en el archivo
        val escribir : BufferedWriter = BufferedWriter(fileWrite)
        escribir.write(cadena)
        //Cerrar el archivo
        escribir.close()
        fileWrite.close()
        // Mostrar los datos
        edt.append(cadena)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Estudiante.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Estudiante().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}