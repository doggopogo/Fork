package ca.etsmtl.log.fitnesshabits.data.database.repositories.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.TypeDao
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Type
import javax.inject.Inject

class TypeRepository @Inject constructor(private val typeDao: TypeDao) {
    // Insert a type
    suspend fun insertType(type: Type): Long {
        return typeDao.insertType(type)
    }

    // Insert multiple types
    suspend fun insertAllTypes(types: List<Type>): List<Long> {
        return typeDao.insertAllTypes(types)
    }

    // Get a type by id
    suspend fun getTypeById(id: Int): Type? {
        return typeDao.getTypeById(id)
    }

    // Get all types by name
    suspend fun getTypeByName(name: String): List<Type> {
        return typeDao.getTypeByName(name)
    }

    // Get all types
    suspend fun getAllTypes(): List<Type> {
        return typeDao.getAllTypes()
    }

    // Delete a type by id
    suspend fun deleteTypeById(id: Int): Int {
        return typeDao.deleteTypeById(id)
    }

    // Update a type by id
    suspend fun updateTypeById(id: Int, name: String): Int {
        return typeDao.updateTypeById(id, name)
    }
}
