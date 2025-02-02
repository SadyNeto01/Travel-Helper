package com.example.travelhelper.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.travelhelper.data.models.PalavrasEIdiomas

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "language_helper.db"
        private const val DATABASE_VERSION = 3
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d("DatabaseHelper", "Criando a tabela palavras_e_idiomas")
        db.execSQL(
            """
            CREATE TABLE palavras_e_idiomas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                pt TEXT NOT NULL COLLATE NOCASE,
                en TEXT NOT NULL COLLATE NOCASE,
                es TEXT NOT NULL COLLATE NOCASE,
                fr TEXT NOT NULL COLLATE NOCASE
            )
            """
        )

        seedDatabase(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d("DatabaseHelper", "Atualizando banco de dados da versão $oldVersion para $newVersion")
        db.execSQL("DROP TABLE IF EXISTS palavras_e_idiomas")
        onCreate(db)
    }

    fun resetDatabase() {
        writableDatabase.use { db ->
            db.execSQL("DROP TABLE IF EXISTS palavras_e_idiomas")
            onCreate(db)
        }
    }

    private fun seedDatabase(db: SQLiteDatabase) {
        Log.d("DatabaseHelper", "Inserindo dados iniciais no banco")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Onde é o hospital?', 'Where is the hospital?', '¿Dónde está el hospital?', 'Où est l’hôpital?')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Quanto custa?', 'How much does it cost?', '¿Cuánto cuesta?', 'Combien ça coûte?')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Estou perdido', 'I am lost', 'Estoy perdido', 'Je suis perdu')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Me ajude, por favor!', 'Help me, please!', '¡Ayúdame, por favor!', 'Aidez-moi, s’il vous plaît!')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Chame a ambulância!', 'Call an ambulance!', '¡Llama a una ambulancia!', 'Appelez une ambulance!')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Chame a polícia!', 'Call the police!', '¡Llama a la policía!', 'Appelez la police!')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Preciso de um médico', 'I need a doctor', 'Necesito un médico', 'J’ai besoin d’un médecin')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Onde fica a casa de banho?', 'Where is the bathroom?', '¿Dónde está el baño?', 'Où sont les toilettes?')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Fale mais devagar, por favor', 'Speak slower, please', 'Hable más despacio, por favor', 'Parlez plus lentement, s’il vous plaît')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Não falo bem essa língua', 'I don’t speak this language well', 'No hablo bien este idioma', 'Je ne parle pas bien cette langue')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Você pode me ajudar?', 'Can you help me?', '¿Me puedes ayudar?', 'Pouvez-vous m’aider?')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Tenho alergia a isso', 'I am allergic to this', 'Soy alérgico a eso', 'Je suis allergique à ça')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Eu gostaria de água', 'I would like some water', 'Quisiera agua', 'Je voudrais de l’eau')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Posso pagar com cartão?', 'Can I pay with a card?', '¿Puedo pagar con tarjeta?', 'Puis-je payer par carte?')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Onde fica o metrô?', 'Where is the subway?', '¿Dónde está el metro?', 'Où est le métro?')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Onde posso pegar um táxi?', 'Where can I get a taxi?', '¿Dónde puedo tomar un taxi?', 'Où puis-je prendre un taxi?')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Preciso de um hotel', 'I need a hotel', 'Necesito un hotel', 'J’ai besoin d’un hôtel')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Tem Wi-Fi aqui?', 'Is there Wi-Fi here?', '¿Hay Wi-Fi aquí?', 'Y a-t-il du Wi-Fi ici?')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Estou doente', 'I am sick', 'Estoy enfermo', 'Je suis malade')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Preciso de um carregador', 'I need a charger', 'Necesito un cargador', 'J’ai besoin d’un chargeur')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Onde posso comprar comida?', 'Where can I buy food?', '¿Dónde puedo comprar comida?', 'Où puis-je acheter de la nourriture?')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Estou com fome', 'I am hungry', 'Tengo hambre', 'J’ai faim')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Estou com sede', 'I am thirsty', 'Tengo sed', 'J’ai soif')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Qual é o número do seu telefone?', 'What is your phone number?', '¿Cuál es tu número de teléfono?', 'Quel est ton numéro de téléphone?')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Eu preciso trocar dinheiro', 'I need to exchange money', 'Necesito cambiar dinero', 'J’ai besoin de changer de l’argent')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Meu voo foi cancelado', 'My flight was canceled', 'Mi vuelo fue cancelado', 'Mon vol a été annulé')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Estou atrasado', 'I am late', 'Estoy atrasado', 'Je suis en retard')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Cuidado!', 'Watch out!', '¡Cuidado!', 'Attention!')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Preciso de um guarda-chuva', 'I need an umbrella', 'Necesito un paraguas', 'J’ai besoin d’un parapluie')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Onde posso alugar um carro?', 'Where can I rent a car?', '¿Dónde puedo alquilar un coche?', 'Où puis-je louer une voiture?')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Onde está minha mala?', 'Where is my suitcase?', '¿Dónde está mi maleta?', 'Où est ma valise?')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('O caixa eletrônico mais próximo?', 'Where is the nearest ATM?', '¿Dónde está el cajero más cercano?', 'Où est le distributeur le plus proche?')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Preciso de um remédio', 'I need medicine', 'Necesito medicina', 'J’ai besoin de médicaments')")

    }
}