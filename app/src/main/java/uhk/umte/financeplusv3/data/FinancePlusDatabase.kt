package uhk.umte.financeplusv3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uhk.umte.financeplusv3.models.Expense
import uhk.umte.financeplusv3.models.Income
import uhk.umte.financeplusv3.models.Transaction
import uhk.umte.financeplusv3.utils.Converters

//class FinancePlusDatabase {}



@Database(entities = [Transaction::class, Income::class, Expense::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FinancePlusDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: FinancePlusDatabase? = null

        fun getDatabase(context: Context): FinancePlusDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FinancePlusDatabase::class.java,
                    "finance_plus_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}