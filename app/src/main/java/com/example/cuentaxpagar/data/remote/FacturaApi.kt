package com.example.cuentaxpagar.data.remote

import com.example.cuentaxpagar.data.remote.dto.FacturaDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FacturaApi {

    @GET("api/facturas/{id}")
    suspend fun getFactura(@Path("id") id: Int): FacturaDto

    @GET("api/facturas")
    suspend fun getFacturas(): List<FacturaDto>

    @PUT("api/facturas/{id}")
    suspend fun putFactura(@Path("id") id: Int, @Body facturaDto: FacturaDto?): Response<FacturaDto>

    @POST("api/facturas")
    suspend fun postFactura(@Body facturaDto: FacturaDto?): FacturaDto?

    @DELETE("api/facturas/{id}")
    suspend fun deleteFactura(@Path("id") id: Int): Response<Unit>
}


