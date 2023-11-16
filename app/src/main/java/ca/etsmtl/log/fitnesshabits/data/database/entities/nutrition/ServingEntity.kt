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
data class Serving(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val size: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val amount: Float,
    @ColumnInfo val unitId: Int
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
            entity = Serving::class,
            parentColumns = ["id"],
            childColumns = ["servingId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["itemId", "servingId"], unique = true),
        Index(value = ["servingId"], unique = false),
        Index(value = ["itemId"], unique = false)]
)
data class ItemServing(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val itemId: Int,
    @ColumnInfo val servingId: Int,
    @ColumnInfo val baseServingMultiplier: Float
)

data class ItemWithServings(    // Makes it easier to fetch all servings for a specific item
    @Embedded val item: Item,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = ItemServing::class,
            parentColumn = "itemId",
            entityColumn = "servingId"
        )
    )
    val servings: List<Serving>
)