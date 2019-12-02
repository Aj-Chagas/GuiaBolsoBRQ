package br.com.ajchagas.guiabolsobrq.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.ajchagas.guiabolsobrq.model.Conta

@Dao
interface ContaDAO {

    @Insert(onConflict = REPLACE)
    fun salva(conta: Conta)

    @Query("select * from Conta ORDER BY id DESC")
    fun buscaTodos(): LiveData<List<Conta>?>

    @Delete
    fun delete(conta : Conta)

}
