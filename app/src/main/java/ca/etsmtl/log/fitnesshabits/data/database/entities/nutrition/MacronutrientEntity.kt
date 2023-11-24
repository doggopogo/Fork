package ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class Macronutrient(
    @PrimaryKey(autoGenerate = true) override val id: Int = 0,
    @ColumnInfo override val name: String,
    @ColumnInfo override val unitId: Int
) : Nutrient

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Item::class,
            parentColumns = ["id"],
            childColumns = ["itemId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["itemId", "macronutrientId"], unique = true),
        Index(value = ["macronutrientId"], unique = false),
        Index(value = ["itemId"], unique = false)
    ]
)
data class ItemMacronutrient(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val itemId: Int,
    @ColumnInfo val macronutrientId: Int,
    @ColumnInfo val amount: Int
)

data class ItemWithMacronutrients(  // Makes it easier to fetch all macronutrients for a specific item
    @Embedded val item: Item,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = ItemMacronutrient::class,
            parentColumn = "itemId",
            entityColumn = "macronutrientId"
        )
    )
    val macronutrients: List<Macronutrient>
)