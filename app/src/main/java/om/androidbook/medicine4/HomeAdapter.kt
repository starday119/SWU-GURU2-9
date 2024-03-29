package om.androidbook.medicine4

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button

import androidx.recyclerview.widget.RecyclerView

class HomeAdapter(private val onItemClickListener: HomeAdapter.OnItemClickListener) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var doseList: List<Dose> = emptyList()

    fun setData(userEmail: String?, dbHelper:DBHelper? = null) {
        if (userEmail != null && dbHelper != null) {
            doseList = dbHelper.getDoseList(userEmail)
            notifyDataSetChanged()
            Log.d("tag", doseList.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.home_medicine_list, parent, false)
        return HomeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder, position: Int) {
        val dose = doseList[position]

        holder.medicineNameTextView.text = dose.name
        holder.medicineDoseTextView.text = "하루 복용량: ${dose.dose}"

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(dose)
        }
        holder.deleteButton.setOnClickListener {
            onItemClickListener.onDeleteButtonClick(dose)
        }
    }

    override fun getItemCount(): Int {
        return doseList.size
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val medicineDoseTextView: TextView = itemView.findViewById(R.id.medicineDoseTextView)
        val medicineNameTextView: TextView = itemView.findViewById(R.id.medicineNameTextView)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }
    interface OnItemClickListener {
        fun onItemClick(dose: Dose)
        fun onDeleteButtonClick(dose: Dose)

    }

}
