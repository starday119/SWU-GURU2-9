package om.androidbook.medicine4

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MedicinelistActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MedicineAdapter
    private val medicines = mutableListOf<Medicine>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_medicine_list)

        dbHelper = DBHelper(this, "DRUG_INFO", null, 2)
        recyclerView = findViewById(R.id.mediclinelistView)
        adapter = MedicineAdapter(medicines, this::onDeleteMedicine)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 검색 기록 데이터를 DB에서 불러옴
        val searchHistoryData = dbHelper.getSearchHistory()

        // 어댑터 생성 및 RecyclerView에 설정
        val searchHistoryRecyclerView: RecyclerView = findViewById(R.id.serchView)
        val searchHistoryAdapter = SearchHistoryAdapter(searchHistoryData)
        searchHistoryRecyclerView.adapter = searchHistoryAdapter

        loadMedicines()
    }

    @SuppressLint("Range", "NotifyDataSetChanged")
    private fun loadMedicines() {
        val cursor = dbHelper.getRecognizedDrugs()
        medicines.clear()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val name = cursor.getString(cursor.getColumnIndex("DRUG_NAME"))
            val group = cursor.getString(cursor.getColumnIndex("THERAPEUTIC_GROUP"))
            val maxDailyDosage = cursor.getString(cursor.getColumnIndex("MAX_DAILY_DOSAGE"))
            val ingredientName = cursor.getString(cursor.getColumnIndex("INGREDIENT_NAME"))
            val contraindications = cursor.getString(cursor.getColumnIndex("CONTRAINDICATIONS"))

            medicines.add(Medicine(id, name, group, maxDailyDosage, ingredientName, contraindications))
        }

        adapter.notifyDataSetChanged()
        cursor.close()
    }

    private fun onDeleteMedicine(medicine: Medicine) {
        dbHelper.deleteRecognizedDrug(medicine.id)
        loadMedicines()
        Toast.makeText(this, "${medicine.name} deleted", Toast.LENGTH_SHORT).show()
    }
}
