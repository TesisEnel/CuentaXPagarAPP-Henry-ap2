package com.example.cuentaxpagar.data.remote

import com.example.cuentaxpagar.data.remote.dto.ProveedorDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProveedorApi {

    @GET("api/proveedores/{id}")
    suspend fun getProveedor(@Path("id") id: Int): ProveedorDto

    @GET("api/proveedores")
    suspend fun getProveedores(): List<ProveedorDto>

    @POST("api/proveedores")
    suspend fun createProveedor(@Body proveedorDto: ProveedorDto): ProveedorDto

    @PUT("api/proveedores/{id}")
    suspend fun updateProveedor(@Path("id") id: Int, @Body proveedorDto: ProveedorDto): ProveedorDto

    @DELETE("api/proveedores/{id}")
    suspend fun deleteProveedor(@Path("id") id: Int): Response<Unit>
}
