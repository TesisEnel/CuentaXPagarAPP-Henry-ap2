package com.example.cuentaxpagar.data.repository

import com.example.cuentaxpagar.data.entities.UsuarioEntity
import com.example.cuentaxpagar.data.local.dao.UsuarioDao
import com.example.cuentaxpagar.data.remote.UsuarioApi
import com.example.cuentaxpagar.data.remote.dto.UsuarioDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UsuarioRepository @Inject constructor(
    private val usuarioDao: UsuarioDao,
    private val usuarioApi: UsuarioApi
) {

    suspend fun saveUsuario(usuario: UsuarioEntity) =
        usuarioDao.saveUsuario(usuario)

    fun getUsuarioLocal(): Flow<List<UsuarioEntity>> =
        usuarioDao.getAllUsuarios()

    suspend fun deleteUsuarioLocal(usuario: UsuarioEntity) =
        usuarioDao.deleteUsuario(usuario)

    suspend fun getUsuarioLocal(usuarioId: Int): UsuarioEntity? =
        usuarioDao.findUsuario(usuarioId)

    suspend fun getUserByEmail(correo: String) =
        usuarioDao.getUsuarioByEmail(correo)

    fun getUsuario(): Flow<Resource<List<UsuarioDto>>> = flow {
        emit(Resource.Loading())
        try {
            val response = usuarioApi.getUsuarios() // Suponiendo que tengas un método en la API para obtener usuarios
            if (response.isSuccessful) {
                emit(Resource.Success(response.body() ?: emptyList()))
            } else {
                emit(Resource.Error("Error al obtener usuarios"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error desconocido"))
        }
    }



    suspend fun deleteUsuario(usuarioId: Int): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val response = usuarioApi.deleteUsuario(usuarioId) // Suponiendo que tengas un método en la API para eliminar un usuario
            if (response.isSuccessful) {
                emit(Resource.Success(Unit))
            } else {
                emit(Resource.Error("Error al eliminar usuario"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error desconocido"))
        }
    }
}
