package com.example.fragments2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    private val botones = arrayOf<Int>(R.id.bitacora, R.id.estudiante, R.id.asignatura, R.id.salaComputo)
    private var queBoton: Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Al entrar coloco inmediatamente el fragment de la bitacora
        reemplazaFragment(Bitacora())

        var botonMenu : ImageButton
        for(indice in 0..botones.size-1){
            botonMenu =  findViewById<ImageButton>(botones[indice])
            println("Indice: $indice    Arreglo:" + botones[indice])
            botonMenu.setOnClickListener(View.OnClickListener(){
                queBoton = indice
                //llamar a la función para reemplazar el fragment
                when(queBoton){
                    0 -> reemplazaFragment(Bitacora())
                    1 -> reemplazaFragment(Estudiante())
                    2 -> reemplazaFragment(Asignatura())
                    3 -> reemplazaFragment(SalaComputo())
                }
            })
        }
    }


    public fun reemplazaFragment(fragment: Fragment){
        //println("Boton: $queBoton")
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.contenedorFragment, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}