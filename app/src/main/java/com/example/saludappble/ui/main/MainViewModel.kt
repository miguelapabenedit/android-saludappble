package com.example.saludappble.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.saludappble.entities.Comida
import com.example.saludappble.entities.Usuario
import com.example.saludappble.repositories.ComidaRepository
import com.example.saludappble.repositories.UsuarioRepository
import com.example.saludappble.util.Tools
import kotlinx.coroutines.*

class MainViewModel(
    private val usuarioRepository: UsuarioRepository,
    private val comidaRepository: ComidaRepository
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

    var nombreUsuario = ""
    var tipoComida = ""
    var usuario = MutableLiveData<Usuario>()
    var comidas = MutableLiveData<List<Comida>>()

    private val dia = Tools.fechaActualMilisegundos()

    //Variables comida
    var comidaPrincipal = MutableLiveData<String>()
    var comidaSecundaria = MutableLiveData<String>()
    var bebida = MutableLiveData<String>()
    var tuvoPostre = MutableLiveData<Boolean>()
    var postre = MutableLiveData<String>()
    var tuvoTentacion = MutableLiveData<Boolean>()
    var tentacion = MutableLiveData<String>()
    var tuvoHambre = MutableLiveData<Boolean>()

    private var _iniciarComidaFragment = MutableLiveData<String>()
    val iniciarComidaFragment: LiveData<String>
        get() = _iniciarComidaFragment

    fun doneIniciarComidaFragment() {
        _iniciarComidaFragment.value = ""
    }

    private var _volverMainFragment = MutableLiveData<Boolean>()
    val volverMainFragment: LiveData<Boolean>
        get() = _volverMainFragment

    fun doneVolverMainFragment() {
        _volverMainFragment.value = false
    }

    init {
        inicializarVariablesComida()
    }

    private fun inicializarVariablesComida(){
        comidaPrincipal.postValue("")
        comidaSecundaria.postValue("")
        bebida.postValue("")
        tuvoPostre.postValue(false)
        postre.postValue("")
        tuvoTentacion.postValue(false)
        tentacion.postValue("")
        tuvoHambre.postValue(false)
    }

    fun getUsuario(){
        uiScope.launch {
            withContext(Dispatchers.IO) {
                usuario.postValue(usuarioRepository.get(nombreUsuario))
            }
        }
    }

    fun getComidas(){
        uiScope.launch {
            withContext(Dispatchers.IO) {
                comidas.postValue(comidaRepository.getAllUsuarioFecha(nombreUsuario, dia))
            }
        }
    }

    fun onAddComida(){
        uiScope.launch {
            val retorno = withContext(Dispatchers.IO) {
                comidaRepository.ultimaComidaDia(nombreUsuario, dia)
            }
            inicializarVariablesComida()
            retorno?.let {
                when(retorno.tipoComida){
                    "DESAYUNO"->{
                        _iniciarComidaFragment.postValue("ALMUERZO")
                    }
                    "ALMUERZO"->{
                        _iniciarComidaFragment.postValue("MERIENDA")
                    }
                    "MERIENDA"->{
                        _iniciarComidaFragment.postValue("CENA")
                    }
                    "CENA"->{
                        _snackbarEventText.postValue("Ya se ingresaron todas las comidas del dia")
                        _showSnackbarEvent.postValue(true)
                    }
                    else -> {
                        _snackbarEventText.postValue("Problemas con la base de datos")
                        _showSnackbarEvent.postValue(true)
                    }
                }
            }?:run {
                _iniciarComidaFragment.postValue("DESAYUNO")
            }
        }

    }

    fun onSwitchPostre(){

    }

    fun onSwitchTentacion(){

    }

    fun onSwitchHambre(){

    }

    fun onBtnGuardar(){
        uiScope.launch {
            if(comidaPrincipal.value!!.isNotEmpty()
                && comidaSecundaria.value!!.isNotEmpty()
                && bebida.value!!.isNotEmpty()
                && validarPostre(tipoComida)
                && validarTentacion()){

                    val comida = Comida(null,nombreUsuario,tipoComida,comidaPrincipal.value!!,comidaSecundaria.value!!,bebida.value!!,postre.value!!,tentacion.value!!,tuvoHambre.value!!,Tools.fechaActualMilisegundos())
                    val respuesta = withContext(Dispatchers.IO) {
                        comidaRepository.insert(comida)
                    }
                    if(respuesta){
                        _volverMainFragment.postValue(true)
                    }else{
                        _snackbarEventText.postValue("No se pudo guardar, intente mas tarde")
                        _showSnackbarEvent.postValue(true)
                    }
            }else{
                _snackbarEventText.postValue("Faltan completar campos")
                _showSnackbarEvent.postValue(true)
            }
        }


    }

    fun validarPostre(tipoComida:String):Boolean{
        var retorno = false
        if(tipoComida == "ALMUERZO" || tipoComida == "CENA"){
            if(tuvoPostre.value!! && postre.value!!.isNotEmpty()){
                retorno = true
            }else if(!tuvoPostre.value!!){
                postre.postValue("")
                retorno = true
            }
        }else{
            tuvoPostre.postValue(false)
            postre.postValue("")
            retorno = true
        }

        return retorno
    }

    fun validarTentacion():Boolean{
        var retorno = false
        if(tuvoTentacion.value!! && tentacion.value!!.isNotEmpty()){
            retorno = true
        }else if(!tuvoTentacion.value!!){
            tentacion.postValue("")
            retorno = true
        }

        return retorno
    }
}