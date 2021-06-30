package com.example.saludappble.ui.login

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.findFragment
import com.example.saludappble.R
import com.example.saludappble.databinding.ActivityLoginBinding
import com.example.saludappble.ui.login.fragments.CrearUsuarioFragment
import com.example.saludappble.ui.login.fragments.LoginFragment
import com.example.saludappble.ui.main.MainActivity
import com.example.saludappble.ui.main.fragments.MainFragment
import com.example.saludappble.util.Tools
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity() {

    private val model : LoginViewModel by viewModel()

    private lateinit var mBinding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mBinding.loginViewModel = model
        mBinding.lifecycleOwner = this
        abrirFragmentLogin()
        setObservables()
    }

    private fun setObservables(){
        model.abrirMainActivity.observe(this, {
            it?.let {
                if (!it.isEmpty()){
                    model.doneAbrirMainActivity()
                    abrirActivityMain(it)
                }
            }
        })

        model.abrirCrearCuenta.observe(this,{
            if(it){
                val crearUsuarioFragment = CrearUsuarioFragment()

                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    replace(R.id.fragmentLoginContainer, crearUsuarioFragment, "usuario")
                }
                model.doneAbrirCrearCuenta()
            }
        })

        model.abrirLogin.observe(this,{
            if(it){
                abrirFragmentLogin()
                model.doneAbrirLogin()
            }
        })

        model.showSnackBarEvent.observe(this, {
            if (it == true) { // Observed state is true.
                Toast.makeText(this,model.snackbarEventText.value, Toast.LENGTH_SHORT).show()
                model.doneShowingSnackbar()
            }
        })
    }

    private fun abrirFragmentLogin(){
        val loginFragment = LoginFragment()

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            replace(R.id.fragmentLoginContainer, loginFragment, "login")
        }
    }

    private fun abrirActivityMain(usuario: String){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("USUARIO", usuario)
        startActivity(intent)
    }

    override fun onBackPressed() {
        supportFragmentManager.findFragmentByTag("login")?.let{
            if(it.isVisible){
                dialogSalir()
            }else{
                abrirFragmentLogin()
            }
        }?:run{
            abrirFragmentLogin()
        }
    }

    fun dialogSalir(){
        AlertDialog.Builder(mBinding.root.context)
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