package com.adwi.spoon.data.local.dao

import com.adwi.spoon.IngredientEntity
import com.adwi.spoon.IngredientEntityQueries

class IngredientDao(
    private val ingredientQueries: IngredientEntityQueries,
) : CRUDInterface<IngredientEntity> {

    override fun getAll(): List<IngredientEntity> {
        return ingredientQueries.getAll().executeAsList()
    }

    override fun getByID(id: String): IngredientEntity {
        return ingredientQueries.getById(id).executeAsOne()
    }

    override fun add(item: IngredientEntity) {
        with(item) {
            ingredientQueries.add(
                name = name,
                image = image,
                amount = amount,
                unit = unit,
                consistency = consistency,
                original = original
            )
        }
    }

    override fun delete(id: String) {
        ingredientQueries.removeById(id)
    }
}