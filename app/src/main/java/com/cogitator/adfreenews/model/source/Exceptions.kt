package com.cogitator.adfreenews.model.source

/**
 * @author Ankit Kumar on 14/09/2018
 */

open class DataSourceException(message: String? = null) : Exception(message)

class RemoteDataNotFoundException : DataSourceException("Data not found in remote data source")

class LocalDataNotFoundException : DataSourceException("Data not found in local data source")