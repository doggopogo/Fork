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
data class Micronutrient(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val unitID: Int
)

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Item::class,
            parentColumns = ["id"],
            childColumns = ["itemId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Micronutrient::class,
            parentColumns = ["id"],
            childColumns = ["micronutrientId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["itemId", "micronutrientId"], unique = true),
        Index(value = ["micronutrientId"], unique = false),
        Index(value = ["itemId"], unique = false)
    ]
)
data class ItemMicronutrient(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val itemId: Int,
    @ColumnInfo val micronutrientId: Int,
    @ColumnInfo val amount: Float
)

data class ItemWithMicronutrients(  // Makes it easier to fetch all micronutrients for a specific item
    @Embedded val item: Item,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = ItemMicronutrient::class,
            parentColumn = "itemId",
            entityColumn = "micronutrientId"
        )
    )
    val micronutrients: List<Micronutrient>
)