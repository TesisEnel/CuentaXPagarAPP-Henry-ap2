package com.example.cuentaxpagar.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuarios")
data class UsuarioEntity (

    @PrimaryKey
    val usuarioId: Int = 0,
    val correo: String? = null,
    val contrasena: String? = null
)