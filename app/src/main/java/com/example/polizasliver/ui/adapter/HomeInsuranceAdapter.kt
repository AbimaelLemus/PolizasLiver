package com.example.polizasliver.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.polizasliver.R
import com.example.polizasliver.data.model.TypeInsuranceEnum
import com.example.polizasliver.databinding.CardHomeInsuranceBinding
import com.example.polizasliver.domain.model.InfoInsuranceItem
import com.example.polizasliver.ui.home.InfoInsuranceInterface

class HomeInsuranceAdapter(
    val listInfoInsurances: List<InfoInsuranceItem>,
    val listener: InfoInsuranceInterface
) :
    RecyclerView.Adapter<HomeInsuranceAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewHolder {
        return CardViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_home_insurance,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(listInfoInsurances[position], listener)
    }

    override fun getItemCount(): Int {
        return listInfoInsurances.size
    }


    class CardViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val binding = CardHomeInsuranceBinding.bind(itemview)
        fun bind(infoInsurance: InfoInsuranceItem, listener: InfoInsuranceInterface) {
            binding.tvHomeNoInsurance.text = infoInsurance.noInsurance
            binding.tvHomeName.text = infoInsurance.clientsName

            val icon = if (infoInsurance.nameInsurance == TypeInsuranceEnum.AUTO.name) {
                R.drawable.car
            } else if (infoInsurance.nameInsurance == TypeInsuranceEnum.TELEFONOS.name) {
                R.drawable.phone
            } else if (infoInsurance.nameInsurance == TypeInsuranceEnum.MASCOTAS.name) {
                R.drawable.pets
            } else {
                R.drawable.person
            }
            binding.ivCardHomeIcon.setBackgroundResource(icon)

            binding.cvHome.setOnClickListener {
                listener.infoInsurance(infoInsurance.noInsurance)
            }

        }
    }
}