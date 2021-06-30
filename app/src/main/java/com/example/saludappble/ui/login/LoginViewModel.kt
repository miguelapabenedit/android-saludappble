package com.example.saludappble.ui.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.saludappble.entities.Usuario
import com.example.saludappble.repositories.UsuarioRepository
import com.example.saludappble.util.Constantes
import com.example.saludappble.util.Tools
import kotlinx.coroutines.*

class LoginViewModel(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _showSnackbarEvent = MutableLiveData<Boolean>()

    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    private var _snackbarEventText = MutableLiveData<String>()

    val snackbarEventText: LiveData<String>
        get() = _snackbarEventText

    //Fragment Login
    var usuario = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    //Fragment crear cuenta
    var nombre = MutableLiveData<String>()
    var apellido = MutableLiveData<String>()
    var dni = MutableLiveData<Long>()
    var sexo = MutableLiveData<String>()
    var fechaNacimiento = MutableLiveData<String>()
    var localidad = MutableLiveData<String>()
    var tratamiento = MutableLiveData<String>()
    var usuarioCrear = MutableLiveData<String>()
    var passwordCrear = MutableLiveData<String>()

    private var _abrirMainActivity = MutableLiveData<String>()
    val abrirMainActivity: LiveData<String>
        get() = _abrirMainActivity

    fun doneAbrirMainActivity(){
        _abrirMainActivity.value = ""
    }

    private var _abrirCrearCuenta = MutableLiveData<Boolean>()
    val abrirCrearCuenta: LiveData<Boolean>
        get() = _abrirCrearCuenta

    fun doneAbrirCrearCuenta(){
        _abrirCrearCuenta.value = false
    }

    private var _abrirLogin = MutableLiveData<Boolean>()
    val abrirLogin: LiveData<Boolean>
        get() = _abrirLogin

    fun doneAbrirLogin(){
        _abrirLogin.value = false
    }

    private var _abrirDatePicker = MutableLiveData<Boolean>()
    val abrirDatePicker: LiveData<Boolean>
        get() = _abrirDatePicker

    fun doneAbrirDatePicker(){
        _abrirDatePicker.value = false
    }

    private var _cerrarFragmentCrearUsuario = MutableLiveData<Boolean>()
    val cerrarFragmentCrearUsuario: LiveData<Boolean>
        get() = _cerrarFragmentCrearUsuario

    fun doneCerrarFragmentCrearUsuario(){
        _cerrarFragmentCrearUsuario.value = false
    }

    fun onBtnLogin(){

        uiScope.launch {

            if(usuario.value.isNullOrEmpty() || password.value.isNullOrEmpty()){
                _snackbarEventText.postValue("Faltan completar campos")
                _showSnackbarEvent.postValue(true)
            }else{
                val respuesta = withContext(Dispatchers.IO) {
                    usuarioRepository.get(usuario.value!!)
                }
                respuesta?.let{
                    if(respuesta.password == password.value){
                        _abrirMainActivity.value = usuario.value!!
                    }
                }
            }
        }

    }

    fun onCrearCuenta(){
        inicializarVariablesCrearUsuario()
        _abrirCrearCuenta.postValue(true)
    }

    fun onBtnGuardar(){
        if(nombre.value!!.isNotEmpty()
                && apellido.value!!.isNotEmpty()
                && dni.value!! != 0L && dni.value!!.toString().length == 8
                && sexo.value!!.isNotEmpty()
                && fechaNacimiento.value!!.isNotEmpty() && fechaNacimiento.value!!.length == 9
                && localidad.value!!.isNotEmpty()
                && tratamiento.value!!.isNotEmpty()
                && usuarioCrear.value!!.isNotEmpty()
                && passwordCrear.value!!.isNotEmpty()){
            uiScope.launch {
                val fechaEnMilis = Tools.fechaMilisegundos(fechaNacimiento.value!!)

                val usuarioCreado = Usuario(null,nombre.value!!,apellido.value!!,dni.value!!,sexo.value!!,fechaEnMilis,localidad.value!!,tratamiento.value!!,usuarioCrear.value!!,passwordCrear.value!!)


                val respuesta = withContext(Dispatchers.IO) {
                    usuarioRepository.insert(usuarioCreado)
                }
                if(respuesta){
                    inicializarVariablesLogin()
                    _abrirLogin.postValue(true)
                }else{
                    _snackbarEventText.postValue("No se pudo guardar el usuario, intente mas tarde")
                    _showSnackbarEvent.postValue(true)
                }
            }

        }else{
            _snackbarEventText.postValue("Faltan completar campos")
            _showSnackbarEvent.postValue(true)
        }

    }

    fun onClickDate(){
        _abrirDatePicker.postValue(true)
    }

    fun onRGTratamiento(view : View){
        when (view.tag) {
            "anorexia" -> tratamiento.postValue(Constantes.TRATAMIENTO_ANOREXIA)
            "bulimia" -> tratamiento.postValue(Constantes.TRATAMIENTO_BULIMIA)
            "obesidad" -> tratamiento.postValue(Constantes.TRATAMIENTO_OBESIDAD)
        }
    }

    fun onSwSexo(view : View){
        when (view.tag) {
            "M" -> sexo.postValue(Constantes.SEXO_MASCULINO)
            "F" -> sexo.postValue(Constantes.SEXO_FEMENINO)
        }
    }

    init {
        inicializarVariablesLogin()
        inicializarVariablesCrearUsuario()
        usuarioAdmin()
    }

    private fun inicializarVariablesLogin(){
        usuario.postValue("")
        password.postValue("")
    }

    private fun inicializarVariablesCrearUsuario(){
        nombre.postValue("")
        apellido.postValue("")
        dni.postValue(0L)
        sexo.postValue(Constantes.SEXO_MASCULINO)
        fechaNacimiento.postValue("")
        localidad.postValue("")
        tratamiento.postValue(Constantes.TRATAMIENTO_ANOREXIA)
        usuarioCrear.postValue("")
        passwordCrear.postValue("")
    }

    private fun usuarioAdmin(){
        uiScope.launch {
            withContext(Dispatchers.IO) {
                usuarioRepository.insert(Usuario(null,"asd","asd",123456789,"asd",123456789,"asd","asd","1","1"))
            }
        }
    }
}