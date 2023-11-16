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
data class BioactiveCompound(
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
            entity = BioactiveCompound::class,
            parentColumns = ["id"],
            childColumns = ["bioactiveCompoundId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["itemId", "bioactiveCompoundId"], unique = true),
        Index(value = ["bioactiveCompoundId"], unique = false),
        Index(value = ["itemId"], unique = false)
    ]
)
data class ItemBioactiveCompound(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val itemId: Int,
    @ColumnInfo val bioactiveCompoundId: Int,
    @ColumnInfo val amount: Float
)

data class ItemWithBioactiveCompounds(  // Makes it easier to fetch all bioactive compounds for a specific item
    @Embedded val item: Item,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = ItemBioactiveCompound::class,
            parentColumn = "itemId",
            entityColumn = "bioactiveCompoundId"
        )
    )
    val bioactiveCompounds: List<BioactiveCompound>
)