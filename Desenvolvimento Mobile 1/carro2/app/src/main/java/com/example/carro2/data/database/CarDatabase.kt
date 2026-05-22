package com.example.carro2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.carro2.data.dao.CarDao
import com.example.carro2.data.entity.Car
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Car::class], version = 1, exportSchema = false)
abstract class CarDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    companion object {
        @Volatile
        private var INSTANCE: CarDatabase? = null

        fun getDatabase(context: Context): CarDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarDatabase::class.java,
                    "car_database"
                )
                    .addCallback(CarDatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class CarDatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database.carDao())
                }
            }
        }

        suspend fun populateDatabase(dao: CarDao) {
            dao.insertCar(
                Car(
                    brand = "Ferrari",
                    model = "Purosangue",
                    price = 3_250_000.00,
                    imageUrl = "https://images.unsplash.com/photo-1592198084033-aade902d1aae?w=800&q=80",
                    description = "O Ferrari Purosangue é o primeiro SUV da história da Ferrari — e também o mais exclusivo. Com DNA de superesportivo e praticidade de um SUV, ele redefiniu os limites do que um carro de luxo pode ser. Cada detalhe foi esculpido para entregar emoção pura em qualquer situação.",
                    engine = "V12 6.5L Aspirado",
                    power = "725 cv",
                    torque = "716 Nm",
                    acceleration = "0-100 km/h em 3,3s",
                    topSpeed = "310 km/h",
                    transmission = "Automático de 8 marchas",
                    origin = "Maranello, Itália"
                )
            )
            dao.insertCar(
                Car(
                    brand = "Nissan",
                    model = "GT-R R35",
                    price = 890_000.00,
                    imageUrl = "https://images.unsplash.com/photo-1544636331-e26879cd4d9b?w=800&q=80",
                    description = "O Nissan GT-R R35, apelidado de 'Godzilla', é uma lenda das pistas que conquistou o mundo por oferecer desempenho de supercar a um preço acessível. Com tração integral inteligente e motor biturbo, ele humilha carros que custam o dobro.",
                    engine = "V6 3.8L Twin-Turbo",
                    power = "570 cv",
                    torque = "637 Nm",
                    acceleration = "0-100 km/h em 2,7s",
                    topSpeed = "315 km/h",
                    transmission = "DCT de 6 marchas",
                    origin = "Tochigi, Japão"
                )
            )
            dao.insertCar(
                Car(
                    brand = "Lamborghini",
                    model = "Aventador SVJ",
                    price = 4_800_000.00,
                    imageUrl = "https://images.unsplash.com/photo-1580273916550-e323be2ae537?w=800&q=80",
                    description = "O Lamborghini Aventador SVJ é a expressão máxima da brutalidade italiana. Com aerodinâmica ativa ALA 2.0 e motor V12 aspirado urrando atrás do piloto, é uma obra de arte mecânica que desafia a física e os sentidos. Detentor do recorde de Nürburgring para carros de produção.",
                    engine = "V12 6.5L Aspirado",
                    power = "770 cv",
                    torque = "720 Nm",
                    acceleration = "0-100 km/h em 2,8s",
                    topSpeed = "350 km/h",
                    transmission = "ISR de 7 marchas",
                    origin = "Sant'Agata Bolognese, Itália"
                )
            )
            dao.insertCar(
                Car(
                    brand = "BMW",
                    model = "X6 M Competition",
                    price = 1_150_000.00,
                    imageUrl = "https://images.unsplash.com/photo-1555215695-3004980ad54e?w=800&q=80",
                    description = "O BMW X6 M Competition é o SUV esportivo que combina o conforto e status de um luxuoso utilitário com o coração de um supercarro. Com motor V8 biturbo, suspensão adaptativa M e design fastback exclusivo, ele transforma cada viagem em uma experiência inesquecível.",
                    engine = "V8 4.4L Twin-Turbo",
                    power = "625 cv",
                    torque = "750 Nm",
                    acceleration = "0-100 km/h em 3,8s",
                    topSpeed = "290 km/h",
                    transmission = "Automático de 8 marchas",
                    origin = "Spartanburg, EUA"
                )
            )
        }
    }
}
