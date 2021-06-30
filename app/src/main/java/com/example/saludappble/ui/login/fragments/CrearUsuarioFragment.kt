package com.example.saludappble.ui.login.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import com.example.saludappble.databinding.CrearUsuarioFragmentBinding
import com.example.saludappble.ui.login.LoginViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import timber.log.Timber
import java.lang.IllegalStateException
import java.util.*

class CrearUsuarioFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var mView: View
    private lateinit var mBinding: CrearUsuarioFragmentBinding

    private val model: LoginViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = CrearUsuarioFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.loginViewModel = model
        binding.lifecycleOwner = viewLifecycleOwner

        this.mBinding = binding
        this.mView = view

        setObservables()

        return view
    }

    private fun setObservables(){
        model.abrirDatePicker.observe(viewLifecycleOwner,{
            if(it){
                showDatePicker()
                model.doneAbrirDatePicker()
            }
        })

        model.cerrarFragmentCrearUsuario.observe(viewLifecycleOwner,{
            backPressed()
            model.doneCerrarFragmentCrearUsuario()
        })
    }

    private fun showDatePicker(){
        val datePicker = DatePickerDialog(
                mView.context,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
        )
        datePicker.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val fecha = "$dayOfMonth/${month+1}/$year"
        model.fechaNacimiento.postValue(fecha)

    }

    private fun backPressed(){
        try{
            requireActivity().onBackPressed()
        }catch (e : IllegalStateException){
            Timber.e(e)
        }
    }
}