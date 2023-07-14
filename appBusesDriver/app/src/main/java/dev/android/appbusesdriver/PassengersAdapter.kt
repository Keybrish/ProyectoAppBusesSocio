package dev.android.appbusesdriver

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.android.appbusesdriver.databinding.ItemPassengerBinding
import dev.android.appbusesdriver.models.Detalle_Compra

class PassengersAdapter(var pasajero: List<Detalle_Compra> = emptyList()) : RecyclerView.Adapter<PassengersAdapter.PassengersAdapterViewHolder>() {
    //crear el viewHolder
    lateinit var setOnClickListener:(Detalle_Compra) -> Unit
    inner class PassengersAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding: ItemPassengerBinding = ItemPassengerBinding.bind(itemView)

        fun bind(pasajero: Detalle_Compra) {
            binding.txtSeat.text = pasajero.id_asiento.toString()
            binding.txtID.text = pasajero.cedula_pasajero
            Picasso.get().load(pasajero.foto_usuario)
                .error(R.drawable.travel)
                .into(binding.imgProfile)

            if (adapterPosition % 2 == 1) {
                binding.btnFrequency.setBackgroundColor(ContextCompat.getColor(binding.btnFrequency.context, R.color.purple))
            } else {
                binding.btnFrequency.setBackgroundColor(ContextCompat.getColor(binding.btnFrequency.context, R.color.gray))
            }

//            binding.root.setOnClickListener {
//                setOnClickListener(pasajero)
//            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassengersAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_passenger, parent, false)
        return PassengersAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: PassengersAdapterViewHolder, position: Int) {
        val pasajeros = pasajero[position]
        holder.bind(pasajeros)
    }

    override fun getItemCount(): Int {
        return pasajero.size
    }
    fun updateListFrequencies(pasajeros:List<Detalle_Compra>){
        this.pasajero=pasajeros
        notifyDataSetChanged()
    }
}