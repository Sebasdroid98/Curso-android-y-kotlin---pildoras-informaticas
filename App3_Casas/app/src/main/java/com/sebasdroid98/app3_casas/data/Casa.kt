package com.sebasdroid98.app3_casas.data

import com.sebasdroid98.app3_casas.R

data class Casa(val id:Int, val nombre:String, val imagenId:Int, val descripcion:String)

object RepositorioCasas{

    val listaCasas = listOf(

        Casa(1, "Casa Mediterránea", R.drawable.casa1, "Casa luminosa frente al mar"),
        Casa(2, "Casa Rústica", R.drawable.casa2, "Ambiente calido en la montaña"),
        Casa(3, "Casa Moderna", R.drawable.casa3, "Casa con diseño elegante")

    )

    // V1 de Función para obtener una casa por su id
    /* fun getCasaPorId(id:Int): Casa? {
        for (casa in listaCasas) {
            if (casa.id == id){
                return casa
            }
        }
        return null
    } */

    // V2 de Función para obtener una casa por su id, version reducida
    fun getCasaPorId(id:Int): Casa? = listaCasas.find { it.id == id }

}