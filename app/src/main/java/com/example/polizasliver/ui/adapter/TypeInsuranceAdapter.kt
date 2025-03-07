package com.example.polizasliver.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.polizasliver.R
import com.example.polizasliver.databinding.CardTypeInsuranceBinding
import com.example.polizasliver.ui.type_insurance.CardTypeInterface
import com.example.polizasliver.data.model.TypeInsuranceModel

class TypeInsuranceAdapter(
    private val listTypeInsurance: List<TypeInsuranceModel>,
    private val listener: CardTypeInterface
) : RecyclerView.Adapter<TypeInsuranceAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_type_insurance,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(listTypeInsurance[position], listener, position)
    }

    override fun getItemCount(): Int {
        return listTypeInsurance.size
    }

    class CardViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val binding = CardTypeInsuranceBinding.bind(itemview)
        fun bind(typeInsuranceModel: TypeInsuranceModel, listener: CardTypeInterface, position: Int) {
            binding.tvCardTypeInsurance.text = typeInsuranceModel.nameTypeInsurance
            binding.ivCardTypeInsurance.setBackgroundResource(typeInsuranceModel.icon)
            binding.cvCardTypeInsurance.setOnClickListener {
                listener.itemCardType(position)
            }
        }

    }
}