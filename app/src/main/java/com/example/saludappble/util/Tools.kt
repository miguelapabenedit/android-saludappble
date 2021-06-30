package com.example.saludappble.util

import java.util.*
import kotlin.time.milliseconds

class Tools {
    companion object{
        fun horaActualMilisegundos(): Long {
            val hActual = Calendar.getInstance()
            hActual.time = Date()
            hActual.timeZone = TimeZone.getTimeZone("GMT-3")
            return hActual.timeInMillis
        }

        fun milisegundosAMinutos(time: Long): String {
            return (time / 60000).toString()
        }

        fun fechaActualMilisegundos(): Long {
            val hActual = Calendar.getInstance().apply{
                time = Date()
                timeZone = TimeZone.getTimeZone("GMT-3")
                set(Calendar.HOUR,0)
                set(Calendar.MINUTE,0)
                set(Calendar.SECOND,0)
                set(Calendar.MILLISECOND,0)
            }
            return hActual.timeInMillis
        }

        fun fechaAnteriorMilisegundos(restarDias : Int): Long {
            val hActual = Calendar.getInstance().apply{
                time = Date(Date().time - diasAMiliSegundos(restarDias))
                timeZone = TimeZone.getTimeZone("GMT-3")
                set(Calendar.HOUR,0)
                set(Calendar.MINUTE,0)
                set(Calendar.SECOND,0)
                set(Calendar.MILLISECOND,0)
            }
            return hActual.timeInMillis
        }

        fun fechaMilisegundos(fecha:String): Long {
            fecha.substringBefore("/")
            val dia = fecha.substringBefore("/").toInt()
            val mes = fecha.substringAfter("/").substringBeforeLast("/").toInt() - 1
            val anio = fecha.substringAfterLast("/").toInt()
            val hActual = Calendar.getInstance().apply{
                set(Calendar.DATE,dia)
                set(Calendar.MONTH,mes)
                set(Calendar.YEAR,anio)
                timeZone = TimeZone.getTimeZone("GMT-3")
                set(Calendar.HOUR,0)
                set(Calendar.MINUTE,0)
                set(Calendar.SECOND,0)
                set(Calendar.MILLISECOND,0)
            }
            return hActual.timeInMillis
        }

        fun diasAMiliSegundos(dias : Int):Long{
            return dias.toLong() * 24 * 60 * 60 * 1000
        }
    }
}