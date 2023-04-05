package uhk.umte.financeplusv3.models

import java.util.Date

class Transaction(

    val id: Int = 0,
    val amount: Double,
    val category: String,
    val date: Date,
    val description: String,
    val transactionType: String
)
/*
@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Double,
    val category: String,
    val date: Date,
    val description: String,
    val transactionType: String
)*/