package com.example.saludappble.ui.main

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.example.saludappble.R
import com.example.saludappble.databinding.ActivityMainBinding
import com.example.saludappble.ui.main.fragments.ComidaFragment
import com.example.saludappble.ui.main.fragments.MainFragment
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private val model : MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    private val mainFragment = MainFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainViewModel = model
        binding.lifecycleOwner = this

        savedInstanceState?.let{
            model.nombreUsuario = savedInstanceState.getString("USUARIO","")
        }?:run{
            val bundle : Bundle? = intent.extras
            bundle?.let{
                model.nombreUsuario = it.getString("USUARIO","")
            }
        }

        model.getUsuario()

        setObservables()

    }

    private fun setObservables(){
        model.iniciarComidaFragment.observe(this,{
            if(it.isNotEmpty()){
                val comidaFragment = ComidaFragment(it)
                model.tipoComida = it
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    replace(R.id.fragmentContainer, comidaFragment,"comida")
                }

                model.doneIniciarComidaFragment()
            }
        })


        model.volverMainFragment.observe(this,{
            if(it){

                abrirFragmentMain()

                model.doneVolverMainFragment()
            }
        })

        model.showSnackBarEvent.observe(this, {
            if (it == true) { // Observed state is true.
                Toast.makeText(this,model.snackbarEventText.value, Toast.LENGTH_SHORT).show()
                model.doneShowingSnackbar()
            }
        })
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        supportFragmentManager.findFragmentByTag("main")?.let{
            if(it.isVisible){
                dialogSalir()
            }else{
                abrirFragmentMain()
            }
        }?:run{
            abrirFragmentMain()
        }
    }

    private fun abrirFragmentMain(){
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            replace(R.id.fragmentContainer, mainFragment,"main")
        }
    }

    fun dialogSalir(){
        AlertDialog.Builder(binding.root.context)
            .setTitle("Salir")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setMessage("¿Desea salir de la aplicación?")
            .setPositiveButton("Si"){ dialog, _ ->
                finishAffinity()
                finish()
                exitProcess(0)
            }
            .setNegativeButton("No"){dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}