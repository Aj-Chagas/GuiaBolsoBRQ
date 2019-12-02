package br.com.ajchagas.guiabolsobrq.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.ajchagas.guiabolsobrq.database.converter.ConverterBigDecimal
import br.com.ajchagas.guiabolsobrq.database.dao.ContaDAO
import br.com.ajchagas.guiabolsobrq.model.Conta

private const val NOME_BANCO_DE_DADOS = "conta.db"

@Database(entities = [Conta::class], version = 2, exportSchema = false)
@TypeConverters(ConverterBigDecimal::class)
abstract class AppDatabase : RoomDatabase(){

    abstract val contaDAO: ContaDAO

    companion object {
        private lateinit var db:AppDatabase

        fun getInstance(context: Context) : AppDatabase {

            if(::db.isInitialized) return db


            db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                NOME_BANCO_DE_DADOS
            )
                .fallbackToDestructiveMigration()
                .build()
            return db
        }
    }

}