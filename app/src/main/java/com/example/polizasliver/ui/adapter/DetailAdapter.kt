package com.example.polizasliver.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.polizasliver.R
import com.example.polizasliver.data.model.TermsModel
import com.example.polizasliver.databinding.CardDetailInsuranceBinding
import com.example.polizasliver.ui.dialogs_alerts.DialogsAlerts
import com.example.polizasliver.ui.home.InfoInsuranceInterface

class DetailAdapter(
    val context: Context,
    private val listTerms: List<TermsModel>
) : RecyclerView.Adapter<DetailAdapter.CardViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewHolder {
        return CardViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_detail_insurance,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(context, listTerms[position])
    }

    override fun getItemCount(): Int {
        return listTerms.size
    }

    class CardViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val binding = CardDetailInsuranceBinding.bind(itemview)
        fun bind(context: Context, termsModel: TermsModel) {
            binding.tvCardDetailInsurance.text = termsModel.typeTerms
            binding.ivCardDetailInsuranceIcon.setBackgroundResource(termsModel.iconTerms)
            binding.cvDetailInsurance.setOnClickListener {
                DialogsAlerts().alertWithouNegative(
                    binding.root.context,
                    termsModel.typeTerms,
                    termsModel.clauses,
                    true,
                    context.getString(R.string.accept)
                )
            }
        }

    }
}